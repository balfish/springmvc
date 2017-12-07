package com.balfish.hotel.zzAlgorithm;

/**
 * Created by yhm on 2017/11/21 AM11:27.
 */
public class MyStack<E> {

    private static final Integer MAX_SIZE = 100;

    private Object[] stack;
    private int size;

    MyStack() {
        stack = new Object[MAX_SIZE];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e) {
        if (size == MAX_SIZE) {
            throw new RuntimeException();
        }
        stack[size++] = e;
    }

    public void pop() {
        if (size == 0) {
            throw new RuntimeException();
        }
        size--;
    }

    public E peek() {
        if (size == 0) {
            return null;
        }
        return (E) stack[size - 1];
    }
}
