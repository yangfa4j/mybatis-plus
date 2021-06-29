package com.test.generic;

/**
 *
 * @param <T>
 */
public class Generic<T> {

    private T name;

    public Generic(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public <E> E m1(E e) {

        System.out.println("e = " + e);
        return e;
    }

    public <K, V> K m2(K k, V v) {
        System.out.println("k = " + k);
        System.out.println("v = " + v);
        return k;
    }

    @Override
    public String toString() {
        return "Generic{" +
                "name=" + name +
                '}';
    }
}

