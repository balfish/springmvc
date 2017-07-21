package com.balfish.hotel.train.concurrent.copyOnWrite;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参照源码写的, 学习下 prepare: Array类的方法
 * <p>
 * Created by yhm on 2017/7/12 PM2:17
 */
public class MyCopyOnWriteArrayList<E> {

    transient final ReentrantLock reentrantLock = new ReentrantLock();

    private volatile transient Object[] array;

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public MyCopyOnWriteArrayList() {
        setArray(new Object[0]);
    }

    public MyCopyOnWriteArrayList(Collection<? extends E> collection) {
        Object[] elements = collection.toArray();
        if (elements.getClass() != Object[].class) {
            elements = Arrays.copyOf(elements, elements.length, Object[].class);
        }
        setArray(elements);
    }

    public MyCopyOnWriteArrayList(E[] toCopyIn) {
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
    }

    public int size() {
        return getArray().length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 返回Object类型数组
     */
    public Object[] toArray() {
        Object[] elements = getArray();
        return Arrays.copyOf(elements, elements.length);
    }

    /**
     * 返回传入的T类型数组
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T a[]) {
        Object[] elements = getArray();
        int len = size();
        if (a.length < len) {
            return (T[]) Arrays.copyOf(elements, len, a.getClass());
        } else {
            System.arraycopy(elements, 0, a, 0, len);
            return a;
        }

    }


    @SuppressWarnings("unchecked")
    private E get(Object[] a, int index) {
        return (E) a[index];
    }

    public E get(int index) {
        return get(getArray(), index);
    }

    public E set(int index, E element) {
        final ReentrantLock reentrantLock = this.reentrantLock;
        reentrantLock.lock();
        try {
            Object[] elements = getArray();
            E oldValue = get(elements, index);

            if (oldValue != element) {
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len);
                newElements[index] = element;
                setArray(newElements);
            } else {
                // 意义何在,没太看懂
                setArray(elements);
            }
            return oldValue;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean add(E e) {
        final ReentrantLock reentrantLock = this.reentrantLock;
        reentrantLock.lock();
        try {
            Object[] elements = getArray();
            int len = size();
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            reentrantLock.unlock();
        }
    }


    public E remove(int index) {
        final ReentrantLock reentrantLock = this.reentrantLock;
        reentrantLock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            E oldValue = get(elements, index);
            int numMoved = len - index - 1;
            if (numMoved == 0)
                setArray(Arrays.copyOf(elements, len - 1));
            else {
                Object[] newElements = new Object[len - 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index + 1, newElements, index,
                        numMoved);
                setArray(newElements);
            }
            return oldValue;
        } finally {
            reentrantLock.unlock();
        }
    }
}

