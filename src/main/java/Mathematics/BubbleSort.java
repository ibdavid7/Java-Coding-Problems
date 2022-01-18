package Mathematics;

import java.util.Arrays;


public class BubbleSort {
    public static void main(String[] args) {
        Integer[] arr = {6, 5, 4, 3, 2, 1, 0};
        String[] arr2 = {"z", "Z", "H", "k", "b", "A"};

        System.out.println(Arrays.toString(bubbleSort(arr)));
        System.out.println(Arrays.toString(bubbleSort(arr2)));

    }

    public static <E extends Comparable> E[] bubbleSort(E[] arr) {

        E[] arrCopy = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arrCopy[j].compareTo(arrCopy[j + 1]) > 0) {
                    E temp = arrCopy[j];
                    arrCopy[j] = arrCopy[j + 1];
                    arrCopy[j + 1] = temp;
                }
            }
        }

        return arrCopy;

    }

}
