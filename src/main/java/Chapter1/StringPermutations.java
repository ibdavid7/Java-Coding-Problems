import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringPermutations {

    public static void main(String[] args) {
        String s1 = "❤😁👍🎂👀";
        Set<String> res = permutationRecursion(s1);
        System.out.println(res);
        System.out.println(res.size());

        String s2 = "012";
        permuteAndPrint(s2);

        String s3 = "AB";
        Stream<String> result = permuteAndReturnStream(s3);
        result.forEach(System.out::println);

    }

    public static Set<String> permutationRecursion(String s) {
        if (s.length() == 0) return new HashSet<>();
        int[] arr = s.codePoints().toArray();
        Set<String> set = new HashSet<>();
        permutationRecursionHelper(arr, 0, 0, set);
        return set;
    }

    public static void permutationRecursionHelper(int[] arr, int indexStart, int indexEnd, Set<String> target) {

        // if starting new string
        if (indexStart == indexEnd && indexStart < arr.length - 1) {
            for (int i = indexStart + 1; i < arr.length; i++) {
                permutationRecursionHelper(arr, indexStart, i, target);
            }
        }

        // swap
        int[] arrNew = swap(arr, indexStart, indexEnd);

        //fix or save
        if (indexStart == arr.length - 1) {
            target.add(joinCodePoints(arrNew));
        } else {
            indexStart++;
            indexEnd = indexStart;
            permutationRecursionHelper(arrNew, indexStart, indexEnd, target);
        }

    }

    public static int[] swap(int[] arr, int from, int to) {
        if (from == to) {
            return arr;
        } else {
            int[] copy = Arrays.copyOf(arr, arr.length);
            int temp = copy[from];
            copy[from] = copy[to];
            copy[to] = temp;
            return copy;
        }
    }

    public static String joinCodePoints(int[] arr) {
        return Arrays.stream(arr)
                .mapToObj(codePoint -> String.valueOf(Character.toChars(codePoint)))
                .collect(Collectors.joining());
    }

    public static void permuteAndPrint(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return;
        }

        permuteAndPrint("", str);
    }

    private static void permuteAndPrint(String prefix, String str) {

//        System.out.println(prefix + " - " + str);

        int n = str.length();
        if (n == 0) {
//            System.out.print(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.println("prefix: " + prefix + str.charAt(i));
                System.out.println("string: " + str.substring(i + 1, n) + str.substring(0, i));
                permuteAndPrint(prefix + str.charAt(i),
                        str.substring(i + 1, n) + str.substring(0, i));
            }
        }
    }

    public static Set<String> permuteAndStore(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return Collections.emptySet();
        }

        return permuteAndStore("", str);
    }

    private static Set<String> permuteAndStore(String prefix, String str) {

        Set<String> permutations = new HashSet<>();

        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutations.addAll(permuteAndStore(prefix + str.charAt(i),
                        str.substring(i + 1, n) + str.substring(0, i)));
            }
        }
        return permutations;
    }

    public static void permuteAndPrintStream(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return;
        }

        permuteAndPrintStream("", str);

    }

    private static void permuteAndPrintStream(String prefix, String str) {

        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n)
                    .parallel()
                    .forEach(i -> permuteAndPrintStream(prefix + str.charAt(i),
                            str.substring(i + 1, n) + str.substring(0, i)));
        }
    }

    public static Stream<String> permuteAndReturnStream(String str) {

        if (str == null || str.isBlank()) {
            return Stream.of("");
        }

//        Stream<String> s =   IntStream.range(0, str.length())
//                .parallel()
//                .boxed()
//                .peek(System.out::println)
//                .flatMap(i -> permuteAndReturnStream(str.substring(0, i) + str.substring(i + 1))
//                        .map(c -> str.charAt(i) + c)
//                );

        return IntStream.range(0, str.length())
//                .parallel()
                .boxed()
                .peek(System.out::println)
                .flatMap(i -> permuteAndReturnStream(str.substring(0, i) + str.substring(i + 1))
                        .peek(System.out::println)
                                .map(c -> str.charAt(i) + c)
                );
    }

}
