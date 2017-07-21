package com.balfish.common.utils.diff;

import com.balfish.common.ScmException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.javers.core.diff.Change;
import org.javers.core.diff.changetype.NewObject;
import org.javers.core.diff.changetype.ObjectRemoved;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.ReferenceChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ArrayChange;
import org.javers.core.diff.changetype.container.ContainerChange;
import org.javers.core.diff.changetype.container.ContainerElementChange;
import org.javers.core.diff.changetype.container.ElementValueChange;
import org.javers.core.diff.changetype.container.ValueAddOrRemove;
import org.javers.core.diff.changetype.container.ValueAdded;
import org.javers.core.diff.changetype.container.ValueRemoved;
import org.javers.core.diff.changetype.map.EntryAdded;
import org.javers.core.diff.changetype.map.EntryChange;
import org.javers.core.diff.changetype.map.EntryRemoved;
import org.javers.core.diff.changetype.map.EntryValueChange;
import org.javers.core.diff.changetype.map.MapChange;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.object.ValueObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
class ChangeView {
    private static final Logger logger = LoggerFactory.getLogger(ChangeView.class);
    private static final String EMPTY_VALUE = "<空>";
    private static Pattern INDEX_PROPERTY_PATTERN = Pattern.compile("(.*)/([0-9]+)");

    private Map<String, ImmutablePair<String, String>> changes = Maps.newHashMap();
    private final Change change;
    private final String property;
    private final Object left, right;

    public ChangeView(Object left, Object right, Change change) {
        this.change = change;
        this.property = getPropertyName(change);
        this.left = left;
        this.right = right;
    }

    private void add(List<ImmutablePair<String, String>> pairList) {
        for (ImmutablePair<String, String> pair : pairList) {
            changes.put(property, pair);
        }
    }

    private void add(ImmutablePair<String, String> pair) {
        changes.put(property, pair);
    }

    Map<String, ImmutablePair<String, String>> process() {
        if (change instanceof PropertyChange) {
            PropertyChange propertyChange = (PropertyChange) change;
            if (propertyChange instanceof ReferenceChange) {
                add(ImmutablePair.of(getPropertyValue(left), getPropertyValue(right)));
            } else if (propertyChange instanceof ValueChange) {
                add(collect((ValueChange) change));
            } else if (propertyChange instanceof MapChange) {
                changes.putAll(collect((MapChange) change));
            } else if (propertyChange instanceof ArrayChange) {
                changes.putAll(collect((ContainerChange) change));
            } else if (propertyChange instanceof ContainerChange) {
                changes.putAll(collect((ContainerChange) change));
            } else {
                throw new ScmException(getErrorMessage(change));
            }
        } else if (change instanceof ObjectRemoved) {
            add(ImmutablePair.of(getPropertyValue(left), EMPTY_VALUE));
        } else if (change instanceof NewObject) {
            add(ImmutablePair.of(EMPTY_VALUE, getPropertyValue(right)));
        } else {
            throw new ScmException(getErrorMessage(change));
        }
        return changes;
    }

    private String getErrorMessage(Change change) {
        return "目前不处理此类变更！(" + change + "), Left = " + left + ", Right = " + right;
    }

    Map<String, ImmutablePair<String, String>> collect(ContainerChange change) {
        List<ContainerElementChange> elementChanges = change.getChanges();
        Map<String, ImmutablePair<String, String>> result = Maps.newHashMap();
        for (ContainerElementChange elementChange : elementChanges) {
            Integer index = elementChange.getIndex();
            String indexedProperty = String.format("%s[%d]", property, index);
            if (elementChange instanceof ElementValueChange) {
                ElementValueChange valueChange = (ElementValueChange) elementChange;
                result.put(indexedProperty, createPair(valueChange.getLeftValue(), valueChange.getRightValue()));
            } else if (elementChange instanceof ValueAddOrRemove) {
                ValueAddOrRemove valueChange = (ValueAddOrRemove) elementChange;
                Object value = valueChange.getValue();
                if (valueChange instanceof ValueAdded) {
                    result.put(indexedProperty, createPair(EMPTY_VALUE, value));
                }
                if (valueChange instanceof ValueRemoved) {
                    result.put(indexedProperty, createPair(value, EMPTY_VALUE));
                }
            }
        }
        return result;
    }

    private String getPropertyValue(Object bean) {
        try {
            String propertyName = this.property;
            Matcher matcher = INDEX_PROPERTY_PATTERN.matcher(propertyName);
            if (matcher.matches()) {
                propertyName = matcher.group(1) + "[" + matcher.group(2) + "]";
            }
            String property = BeanUtils.getProperty(bean, propertyName);
            if (null == property) {
                return EMPTY_VALUE;
            }
            return property;
        } catch (Exception e) {
            logger.error("无法获取属性{}, Bean: {} ", property, left, e);
            return "<错误>";
        }
    }

    private String getPropertyName(Change change) {
        List<String> propertyPath = Lists.newArrayList();
        if (change instanceof PropertyChange) {
            String propertyName = ((PropertyChange) change).getProperty().getName();
            propertyPath.add(propertyName);
        }
        fragmentPath(change.getAffectedGlobalId(), propertyPath);
        Collections.reverse(propertyPath);
        return StringUtils.join(propertyPath, ".");
    }

    private void fragmentPath(GlobalId globalId, List<String> path) {
        if (globalId instanceof ValueObjectId) {
            ValueObjectId valueObjectId = (ValueObjectId) globalId;
            path.add(valueObjectId.getFragment());
            fragmentPath(valueObjectId.getOwnerId(), path);
        }
    }

    private Map<String, ImmutablePair<String, String>> collect(MapChange change) {
        Map<String, ImmutablePair<String, String>> result = Maps.newHashMap();
        List<EntryChange> entryChanges = change.getEntryChanges();
        for (EntryChange entryChange : entryChanges) {
            String key = property + "." + entryChange.getKey();
            if (entryChange instanceof EntryValueChange) {
                EntryValueChange valueChange = (EntryValueChange) entryChange;
                result.put(key, createPair(valueChange.getLeftValue(), valueChange.getRightValue()));
            } else if (entryChange instanceof EntryAdded) {
                EntryAdded entryAdded = (EntryAdded) entryChange;
                result.put(key, createPair(EMPTY_VALUE, entryAdded.getValue()));
            } else if (entryChange instanceof EntryRemoved) {
                EntryRemoved entryRemoved = (EntryRemoved) entryChange;
                result.put(key, createPair(entryRemoved.getValue(), EMPTY_VALUE));
            } else {
                throw new ScmException(getErrorMessage(change));
            }
        }
        return result;
    }

    private List<ImmutablePair<String, String>> collect(ValueChange change) {
        return Collections.singletonList(createPair(change.getLeft(), change.getRight()));
    }

    private String toString(Object value) {
        if (value instanceof Date) {
            return String.valueOf(((Date) value).getTime());
        }
        if (value == null) {
            return EMPTY_VALUE;
        }
        return String.valueOf(value);
    }

    private ImmutablePair<String, String> createPair(Object left, Object right) {
        return ImmutablePair.of(toString(left), toString(right));
    }
}
