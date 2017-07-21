package com.balfish.common.utils.diff;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

import com.balfish.common.ScmException;
import com.balfish.common.utils.json.JsonUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.concurrent.AtomicInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.custom.CustomPropertyComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 变更记录生成器，注意此类线程不安全。 例子： <code>
 * String changes = BeanChanges.startBuild().setLeft(..).setRight(...).toJson()
 * if(changes != BeanChanges.EMPTY_CHANGE) {
 * //有变化
 * }
 * </code> 如果需要忽略某个Bean属性，可使用 {@link org.javers.core.metamodel.annotation.DiffIgnore} 标注
 * </p>
 * 本类是对Javers库的一个简单封装。参见 http://javers.org 获取更多的信息。
 */
// NOTE: 此类仅仅是Javers的一个简单封装，未来需求变化时建议不要单纯的扩展此类功能，应优先考虑使用Javers框架的其他功能
public class JaversBean {
    public static final String EMPTY_CHANGE = "[]";
    private JaversBuilder builder = JaversBuilder.javers();
    private AtomicInitializer<Javers> javersInitializer = new AtomicInitializer<Javers>() {
        @Override
        protected Javers initialize() throws ConcurrentException {
            return builder.build();
        }
    };

    public <T> void registerComparator(CustomPropertyComparator<T, ?> comparator, Class<T> customType) {
        builder.registerCustomComparator(comparator, customType);
        builder.registerValue(customType);
    }

    public <T> void setCustomComparators(List<ValueComparator<T>> comparators) {
        for (ValueComparator<T> comparator : comparators) {
            registerComparator(comparator, comparator.getValueClass());
        }
    }

    private void argumentCheck(Object left, Object right) {
        Preconditions.checkNotNull(left, "值不能为null!");
        Preconditions.checkNotNull(right, "值不能为null!");
        Class<?> aClass = left.getClass();
        Class<?> otherClass = right.getClass();
        Preconditions.checkArgument((aClass.equals(otherClass)), "必须是同一个Class的对象！(" + aClass.getName() + "!="
                + otherClass.getName() + ")");
    }

    String toJson(Object left, Object right, Javers javers) {
        argumentCheck(left, right);
        List<Object[]> changes = Lists.newArrayList();
        Diff diff = javers.compare(left, right);
        Map<String, ImmutablePair<String, String>> result = Maps.newHashMap();
        if (diff.hasChanges()) {
            List<Change> diffChanges = diff.getChanges();
            for (Change change : diffChanges) {
                ChangeView cv = new ChangeView(left, right, change);
                result.putAll(cv.process());
            }
        } else {
            return EMPTY_CHANGE;
        }
        Set<Map.Entry<String, ImmutablePair<String, String>>> entries = result.entrySet();
        for (Map.Entry<String, ImmutablePair<String, String>> entry : entries) {
            changes.add(new Object[]{entry.getKey(), entry.getValue().getLeft(), entry.getValue().getRight()});
        }
        String json = JsonUtils.toJson(changes);
        if (null == json) {
            throw new ScmException("JSON序列化失败！");
        }
        return json;
    }

    /**
     * 生成JSON格式的变更记录，格式为[[属性名1, 左值,右值]...[属性名n, 左值,右值]] "
     *
     * @return JSON格式的变更记录，如果没有变更，返回 {@link JaversBean#EMPTY_CHANGE}
     * @throws ScmException 如果JSON序列失败
     * @see {@link org.javers.core.changelog.SimpleTextChangeLog}
     */
    public String diff(Object left, Object right) {
        try {
            return toJson(left, right, javersInitializer.get());
        } catch (ConcurrentException e) {
            throw new Error("It's impossible!", e);
        }
    }

}
