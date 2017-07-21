package com.balfish.common.utils.diff;

import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.CustomPropertyComparator;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public interface ValueComparator<T> extends CustomPropertyComparator<T, ValueChange> {

    Class<T> getValueClass();

}
