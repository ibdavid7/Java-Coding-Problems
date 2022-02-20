package GoogleKickStarter;


//Round H 2021

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransformTheString {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        Scanner in = new Scanner(System.in);

        int t = Integer.parseInt(in.nextLine()); // Test cases
//        System.out.println(t);
        for (int i = 1; i <= t; ++i) {
            String s = in.nextLine();
            String f = in.nextLine();

//            System.out.println(s);
//            System.out.println(f);

            f =
                    f.chars().collect(HashSet::new, (set, ch) -> set.add((char) ch), HashSet::addAll).stream().map(ch -> Character.toString((char) ch)).collect(Collectors.joining());

            System.out.println("Case #" + i + ": " + handleCase(s, f));

        }

    }

    public static int compareChars(char s, char f) {

        int stepCountFwd = Math.abs(f - s);
        int stepCountBwd = 26 - stepCountFwd;
//        System.out.printf("Forward: %d; Backwards: %d\n", stepCountFwd, stepCountBwd);
        return Math.min(stepCountFwd, stepCountBwd);


    }

    public static int handleCase(String s, String f) {

        Map<Character, Integer> dict = new HashMap<>();

        return s.chars()
                .map(ch -> {

                    if (!dict.containsKey((char) ch)) {

//                        System.out.println((char) ch);

                        Map.Entry<Character, Integer> entry = minStepCount((char) ch, f);

//                        System.out.println(entry.getKey() + "; " + entry.getValue());

                        dict.put(entry.getKey(), entry.getValue());

                    }
                    return dict.get((char) ch);
                })
                .sum();


    }

    public static Map.Entry<Character, Integer> minStepCount(char c, String f) {

        if (f.contains(Character.toString(c))) {
//            System.out.println(Character.toString(c));
            return Map.entry(c, 0);
        }

        return Map.entry(c,
                f.chars()
                        .map(ch -> compareChars(c, (char) ch))
//                        .peek(System.out::println)
                        .min()
                        .getAsInt()
        );
    }


}
