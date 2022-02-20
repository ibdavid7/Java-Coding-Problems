package GoogleKickStarter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HIndex {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));


        int t = Integer.parseInt(in.nextLine()); // Test cases
        for (int i = 1; i <= t; ++i) {

            int p = Integer.parseInt(in.nextLine());

            int[] citations = Arrays.stream(in.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

//            Arrays.sort(papers, Comparator.comparingInt(a -> (int) a).reversed());

//            System.out.println(Arrays.toString(papers));


            int[] hIndexArr = new int[citations.length];
            int[] sortedArr = new int[citations.length];

            int currHIndex = 0;

            for (int j = 0; j < citations.length; j++) {

                int insertionIndex = 0;

                while (insertionIndex <= j && sortedArr[insertionIndex] >= citations[j]) {
                    insertionIndex++;
                }

                for (int k = 0; k < j - insertionIndex; k++) {
                    sortedArr[j - k] = sortedArr[j - k - 1];
                }

                sortedArr[insertionIndex] = citations[j];

                currHIndex = hIndex(sortedArr, currHIndex);
                hIndexArr[j] = currHIndex;

            }

            System.out.printf("Case #%d: %s\n", i,
                    Arrays.stream(hIndexArr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        }

    }

    public static int hIndex(int[] sortedArr, int currHIndex) {

//        // Sort arr[] in asc
//        Arrays.sort(arr);
//
//        // reverse in place
//        for (int i = 0; i < arr.length / 2; i++) {
//
//            int temp = arr[i];
//            arr[i] = arr[arr.length - 1 - i];
//            arr[arr.length - 1 - i] = temp;
//
//        }
        // calc H Index

        return  sortedArr[currHIndex] >= currHIndex + 1 ? currHIndex + 1 : currHIndex;

//        int i = 0;
//        while (i < sortedArr.length && sortedArr[i] >= i + 1) {
//            i++;
//        }
//
//        return i;
    }


}
