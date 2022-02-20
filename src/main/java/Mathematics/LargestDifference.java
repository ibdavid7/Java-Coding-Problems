package Mathematics;

public class LargestDifference {


    public static int largestDifference(int[] arr) {

        int largestDiff = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            int diff = Math.abs(arr[i + 1] - arr[i]);

            if (diff > largestDiff) {
                largestDiff = diff;
            }

        }

        return largestDiff;


    }

}
