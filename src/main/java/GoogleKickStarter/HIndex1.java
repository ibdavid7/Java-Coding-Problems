package GoogleKickStarter;

import java.util.Arrays;
import java.util.Scanner;

public class HIndex1 {

    public static int[] getHIndexScore(int[] citationsPerPaper) {
        int[] hIndex = new int[citationsPerPaper.length];

        // TODO: Add logic to calculate h-index score for each paper

        // Copy Array
        int[] arr = Arrays.copyOf(citationsPerPaper, citationsPerPaper.length);

//        Sort arr[] in asc
        Arrays.sort(arr);

        // reverse in place
        for (int i = 0; i < arr.length / 2; i++) {

            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;

        }
//        calc H Index
        int i = 0;
        while (i < arr.length && arr[i] >= i + 1) {
            i++;
        }

        // End state of H index known, now work backwards





        int currHIndex = 0;

//        for (int i = 0; i < citationsPerPaper.length; i++) {
//
//            if (citationsPerPaper[i] > currHIndex) {
//                boolean increment = false;
//
//                int counter = 0;
//                for (int j = 0; j <= i; j++) {
//                    if (citationsPerPaper[j] > currHIndex) {
//                        counter++;
//                    }
//                    if (counter > currHIndex) {
//                        increment = true;
//                        break;
//                    }
//                }
//
//                currHIndex = increment ? currHIndex + 1 : currHIndex;
//            }
//
//            hIndex[i] = currHIndex;
//
//        }
        return hIndex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Get number of test cases in input
        int testCaseCount = scanner.nextInt();
        // Iterate through test cases
        for (int tc = 1; tc <= testCaseCount; ++tc) {
            // Get number of papers for this test case
            int paperCount = scanner.nextInt();
            // Get number of citations for each paper
            int[] citations = new int[paperCount];
            for (int p = 0; p < paperCount; ++p) {
                citations[p] = scanner.nextInt();
            }
            // Print h-index score after each paper in this test case
            System.out.print("Case #" + tc + ":");
            for (int score : getHIndexScore(citations)) {
                System.out.append(" ").print(score);
            }
            System.out.println();
        }
    }
}
