package main.java.MoshGenerics;

import java.util.Arrays;

public class GenericList<E extends Comparable<? super E>> {

    private E[] arr;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public GenericList(int i) {
        this.arr = (E[]) new Comparable[i];
        size = 0;
    }

    public GenericList() {
        this(DEFAULT_SIZE);

    }

    public void add(E item) {
        if (this.size == this.arr.length) {
            this.arr = Arrays.copyOf(arr, arr.length * 2);
        }

        arr[size++] = item;
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds");
        }
        return this.arr[index];
    }

    public int getSize() {
        return size;
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
