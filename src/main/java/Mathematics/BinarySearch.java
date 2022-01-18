package Mathematics;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6};

        int x = 0;

        System.out.println(binarySearch(x, arr));

    }

    public static int binarySearch(int x, int[] arr) {

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {

            int mid = (start + end) / 2;

            if (arr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }

        return arr[start] == x ? start : -1;

    }


}
