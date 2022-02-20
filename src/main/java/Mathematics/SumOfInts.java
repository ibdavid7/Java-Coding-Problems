package Mathematics;

public class SumOfInts {

    public static int sumOfInts(int[] arr) {

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;

    }


}
