package Mathematics;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        Integer[] arr = {3, 2, 4, 1, 5};

        String[] arr2 = {"H", "z", "F", "a", "A"};

        System.out.println(Arrays.toString(insertionSort(arr)));
        System.out.println(Arrays.toString(insertionSort(arr2)));

    }

    public static <E extends Comparable> E[] insertionSort(E[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int j;

            for (j = 0; arr[j].compareTo(arr[i]) <= 0 && ++j < i; ) {
//                System.out.printf("i: %d; j: %d\n", i, j);
            }

            E temp = arr[i];

            for (int k = 0; k < i - j; k++) {
                arr[i - k] = arr[i - k - 1];
            }

            arr[j] = temp;
//            System.out.printf("i: %d; j: %d\n", i, j);
//            System.out.println(Arrays.toString(arr));

        }

        return arr;

    }

    // Not required, sorting done in-situ
    public static <E extends Comparable> E[] insertElement(E[] arr, int arrEndIndex, E elem, int insertIndex) {

        // Arr already full OR insertIndex outOfBoundsException OR insertIndex beyond arrEndIndex + 1 OR insertIndex < 0
        if (arrEndIndex >= arr.length - 1 || insertIndex > arr.length - 1 || insertIndex > arrEndIndex + 1 || insertIndex < 0)
            return arr;

        for (int i = 0; i < arrEndIndex + 1 - insertIndex; i++) {
            arr[arrEndIndex - i + 1] = arr[arrEndIndex - i];
        }

        arr[insertIndex] = elem;

        return arr;

    }


}
