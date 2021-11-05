package main.java.MoshGenerics;

import java.util.Arrays;

public class ListCustomObj {
    private Object[] arr;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public ListCustomObj(int i) {
        this.arr = new Object[i];
        size = 0;
    }

    public ListCustomObj() {
        this(DEFAULT_SIZE);

    }

    public void add(Object item) {
        if (this.size == this.arr.length) {
            this.arr = Arrays.copyOf(arr, arr.length * 2);
        }

        arr[size++] = item;
    }

    public Object get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return this.arr[index];
    }

    @Override
    public String toString() {
        return "ListCustomInt{" +
                "arr=" + Arrays.toString(Arrays.copyOfRange(arr, 0, size)) +
                '}';
    }

    public void print() {
        System.out.println(this.toString());
    }
}