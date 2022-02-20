package GoogleKickStarter;

import java.util.Scanner;
import java.util.TreeMap;

public class HIndexTreeMap {

    private static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static int[] getHIndexScore(int[] citationsPerPaper) {
        int[] hIndex = new int[citationsPerPaper.length];

        // TODO: Add logic to calculate h-index score for each paper

        // use TreeMap to track the evolution of the state

        // Rules:
        // 1. Ignore elements same or smaller than currHIndex
        // 2. Only add elements bigger than currHIndex -> currHIndex ++
        // 3. If added an element and element at top equal to currHIndex, decrement element at the top; until it's
        // eliminated -> currHIndex--

        int currHIndex = 0;

        for (int i = 0; i < citationsPerPaper.length; i++) {

            // Rule #1 & #2 impl.
            if (citationsPerPaper[i] > currHIndex) {
                // Rule #2 impl.
//                System.out.printf("Start: i: %d; currHIndex: %d, map: %s\n", i, currHIndex, treeMap);
                treeMap.compute(citationsPerPaper[i], (k, v) -> (v == null) ? 1 : v + 1);
                currHIndex++;

                // Rule #3 impl.
                if (treeMap.firstKey() == currHIndex - 1) {
//                    System.out.printf("i: %d; currHIndex: %d; First Key: %d; Count: %d \n", i,
//                            currHIndex, treeMap.firstKey(), treeMap.get(treeMap.firstKey()));
                    treeMap.compute(treeMap.firstKey(), (k, v) -> (v > 1) ? v - 1 : null);
                    currHIndex--;
                }
            } else {
//                System.out.printf("i: %d; elem: %d Element Ignored\n", i, citationsPerPaper[i]);
            }

//            System.out.printf("Finish: i: %d; currHIndex: %d, map: %s\n", i, currHIndex, treeMap);

            hIndex[i] = currHIndex;
        }

        treeMap = new TreeMap<>();

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
