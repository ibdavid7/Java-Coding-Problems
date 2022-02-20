package GoogleKickStarter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class MilkTea {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCases = in.nextInt();
        for (int testCase = 1; testCase <= testCases; testCase++) {
            int numberOfFriends = in.nextInt();
            int numberOfForibbenTeas = in.nextInt();
            int optionsOffered = in.nextInt();
            int[][] friendsOrders = new int[numberOfFriends][optionsOffered];
            for (int friend = 0; friend < numberOfFriends; friend++) {
                String currentFriendOrder = in.next().trim();
                for (int option = 0; option < optionsOffered; option++) {
                    friendsOrders[friend][option] =
                            Character.getNumericValue(currentFriendOrder.charAt(option));
                }
            }

            int[][] forbiddenOrders = new int[numberOfForibbenTeas][optionsOffered];
            for (int forbiddenIndex = 0; forbiddenIndex < numberOfForibbenTeas; forbiddenIndex++) {
                String currentForbiddenTeaOrder = in.next().trim();
                for (int option = 0; option < optionsOffered; option++) {
                    forbiddenOrders[forbiddenIndex][option] =
                            Character.getNumericValue(currentForbiddenTeaOrder.charAt(option));
                }
            }
            int ans = findSmallestNumberOfComplaints(friendsOrders, forbiddenOrders, optionsOffered);
            System.out.println("Case #" + testCase + ": " + ans);
        }
    }

    static int findSmallestNumberOfComplaints(int[][] friendsOrders, int[][] forbiddenOrders, int optionsOffered) {
        // TODO: implement this method to find the smallest number of complaints given that Shakti will
        // only be getting 1 type of tea for his friends.

//        System.out.println(arrToString(friendsOrders));
//        System.out.println(arrToString(forbiddenOrders));

        Map<Integer, int[]> map = new HashMap<>();

        int[] ones = new int[optionsOffered];
        int[] zeros = new int[optionsOffered];
        int[] marginDiff = new int[optionsOffered];
        int sumOfComplainsForOptimum = 0;


        for (int i = 0; i < friendsOrders.length; i++) {
            for (int j = 0; j < optionsOffered; j++) {

                ones[j] = friendsOrders[i][j] == 1 ? ones[j] + 1 : ones[j];
                zeros[j] = friendsOrders[i][j] == 0 ? zeros[j] + 1 : zeros[j];

            }
        }

        for (int i = 0; i < optionsOffered; i++) {
            marginDiff[i] = Math.abs(ones[i] - zeros[i]);
        }

        Map<Integer, List<Integer>> mapDiff = new TreeMap<>();

        IntStream.range(0, optionsOffered)
                .forEach(index -> {

                    if (mapDiff.containsKey(marginDiff[index])) {
                        mapDiff.get(marginDiff[index]).add(index);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(index);
                        mapDiff.put(marginDiff[index], list);
                    }

                });


        int[] optimal = new int[optionsOffered];
        for (int i = 0; i < optionsOffered; i++) {
            optimal[i] = ones[i] >= zeros[i] ? 1 : 0;
            sumOfComplainsForOptimum += optimal[i] == 1 ? zeros[i] : ones[i];
        }

//        System.out.println("1s: " + Arrays.toString(ones));
//        System.out.println("0s: " + Arrays.toString(zeros));
//        System.out.println("Margin: " + Arrays.toString(marginDiff));
//        System.out.println("Compl Opt: " + sumOfComplainsForOptimum);
//        System.out.println("Optimal: " + Arrays.toString(optimal));


        int[] permissible = Arrays.copyOf(optimal, optimal.length);
        int indexCounter = 0;

        ArrayList<ArrayList<Map.Entry<Integer, Integer>>> subSetsOfModifications = generateSubSets(marginDiff);

        int addComplSum = 0;

        while (isForbidden(forbiddenOrders, permissible)) {
            addComplSum = 0;

//            System.out.println("iterating...");

            List<Map.Entry<Integer, Integer>> mod = subSetsOfModifications.get(indexCounter++);

            permissible = Arrays.copyOf(optimal, optimal.length);


            for (int i = 0; i < mod.size(); i++) {

                Map.Entry<Integer, Integer> modEntry = mod.get(i);
                permissible[modEntry.getKey()] = permissible[modEntry.getKey()] == 1 ? 0 : 1;
                int addCost = modEntry.getValue();
                addComplSum += addCost;
            }

//            System.out.println("Mod: " + mod);
//            System.out.println("Permissible: " + Arrays.toString(permissible));

        }

        return sumOfComplainsForOptimum + addComplSum;
    }

    static boolean isForbidden(int[][] forbidden, int[] permissible) {

        for (int i = 0; i < forbidden.length; i++) {
            if (Arrays.equals(forbidden[i], permissible)) {
                return true;
            }
        }

        return false;
    }

    static ArrayList<ArrayList<Map.Entry<Integer, Integer>>> generateSubSets(int[] diff) {


        // Map.Entry<Index, DiffVal>
        List<Map.Entry<Integer, Integer>> listOfDiffs = IntStream.range(0, diff.length)
                .mapToObj(i -> Map.entry(i, diff[i]))
                .collect(ArrayList<Map.Entry<Integer, Integer>>::new, ArrayList::add, ArrayList::addAll);


        listOfDiffs.sort(Comparator.comparingInt(Map.Entry::getValue));

//        System.out.println("ListOfDiffs:" + listOfDiffs);

        ArrayList<ArrayList<Map.Entry<Integer, Integer>>> subSets = new ArrayList<>();

        // TODO: Presently max P is 10
        for (int i = 0; i < Math.min(10, listOfDiffs.size()); i++) {
            Map.Entry<Integer, Integer> mapEntry = listOfDiffs.get(i);
            ArrayList<ArrayList<Map.Entry<Integer, Integer>>> temp = new ArrayList<>();

            subSets.forEach(list -> {

                ArrayList<Map.Entry<Integer, Integer>> copy = new ArrayList<>(list);
                copy.add(mapEntry);
                temp.add(copy);

            });

            ArrayList<Map.Entry<Integer, Integer>> singleton = new ArrayList<>();
            singleton.add(mapEntry);
            temp.add(singleton);


            subSets.addAll(temp);

//            System.out.println("Temp: " + temp);

        }

        subSets.sort(Comparator.comparingInt(list -> list.stream()
                .mapToInt(Map.Entry::getValue)
                .sum()));

        return subSets;

    }


    static String arrToString(int[][] arr2D) {
        return "[" +
                Arrays.stream(arr2D).map(Arrays::toString).collect(Collectors.joining(",\n"))
                +
                "]";
    }
}