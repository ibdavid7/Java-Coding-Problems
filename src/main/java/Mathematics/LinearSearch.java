package Mathematics;

public class LinearSearch {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5};

        int x = 6;

        System.out.println(linearSearch(x, arr));

    }

    public static int linearSearch(int x, int[] arr) {

        int index = 0;

        while (index < arr.length && x != arr[index]) {
            index++;
        }

        if (index < arr.length) {
            return index;
        } else {
            return -1;
        }

    }


}
