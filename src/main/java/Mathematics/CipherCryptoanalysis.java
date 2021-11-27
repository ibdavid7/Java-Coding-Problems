package main.java.Mathematics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CipherCryptoanalysis {

    public static void main(String[] args) {

        String text = "lrvmnir bpr sumvbwvr jx bpr lmiwv yjeryrkbi jx qmbm wi\n" +
                "bpr xjvni mkd ymibrut jx irhx wi bpr riirkvr jx\n" +
                "ymbinlmtmipw utn qmumbr dj w ipmhh but bj rhnvwdmbr bpr\n" +
                "yjeryrkbi jx bpr qmbm mvvjudwko bj yt wkbrusurbmbwjk\n" +
                "lmird jk xjubt trmui jx ibndt\n" +
                "wb wi kjb mk rmit bmiq bj rashmwk rmvp yjeryrkb mkd wbi\n" +
                "iwokwxwvmkvr mkd ijyr ynib urymwk nkrashmwkrd bj ower m\n" +
                "vjyshrbr rashmkmbwjk jkr cjnhd pmer bj lr fnmhwxwrd mkd\n" +
                "wkiswurd bj invp mk rabrkb bpmb pr vjnhd urmvp bpr ibmbr\n" +
                "jx rkhwopbrkrd ywkd vmsmlhr jx urvjokwgwko ijnkdhrii\n" +
                "ijnkd mkd ipmsrhrii ipmsr w dj kjb drry ytirhx bpr xwkmh\n" +
                "mnbpjuwbt lnb yt rasruwrkvr cwbp qmbm pmi hrxb kj djnlb\n" +
                "bpmb bpr xjhhjcwko wi bpr sujsru msshwvmbwjk mkd\n" +
                "wkbrusurbmbwjk w jxxru yt bprjuwri wk bpr pjsr bpmb bpr\n" +
                "riirkvr jx jqwkmcmk qmumbr cwhh urymwk wkbmvb";

        Map<String, String> mapping = Map.ofEntries(
                Map.entry("b", "t"),
                Map.entry("p", "h"),
                Map.entry("r", "e"),
                Map.entry("m", "a"),
                Map.entry("k", "n"),
                Map.entry("d", "d"),
                Map.entry("j", "o"),
                Map.entry("w", "i"),
                Map.entry("i", "s"),
                Map.entry("x", "f"),
                Map.entry("v", "c"),
                Map.entry("u", "r"),
                Map.entry("o", "g"),
                Map.entry("n", "u"),
                Map.entry("s", "p"),
                Map.entry("h", "l"),
                Map.entry("a", "x"),
                Map.entry("l", "b"),
                Map.entry("t", "y"),
                Map.entry("y", "m"),
                Map.entry("c", "w"),
                Map.entry("q", "k"),
                Map.entry("e", "v"),
                Map.entry("f", "q"),
                Map.entry("g", "z")
        );

        Map<String, Long> textMap = buildMap(text);
        Map<String, Double> statsMap = getStats(textMap);
        String cleanTest = decipherText(text, mapping);

        System.out.println(textMap);
        System.out.println(statsMap);
        System.out.println(cleanTest);


        String text2 = "xultpaajcxitltlxaarpjhtiwtgxktghidhipxciwtvgtpilpitghlxiwiwtxgqadds";
        String text3 = "a";
        Map<String, Long> textMap2 = buildMap(text2);
        Map<String, Double> statsMap2 = getStats(textMap2);
        String cleanText2 = shiftCipher(text2, -15);
        String cleanText3 = shiftCipher(text3, -1);

        System.out.println(textMap2);
        System.out.println(statsMap2);
        System.out.println(cleanText2);
        System.out.println(cleanText3);

//        System.out.println(getInverseMultiplier(13, 26));

        String affineCiphedText = "falszztysyjzyjkywjrztyjztyynaryjkyswarztyegyyj";
        String affineCipherCleanText = decriptAffineCipher(affineCiphedText, 7, 22);
        System.out.println(affineCipherCleanText);

        System.out.println(getInverseMultiplier(17, 30));

    }

    public static Map<String, Long> buildMap(String s) {

        return s.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.toMap(
                        Function.identity(),
                        v -> 1L,
                        Long::sum,
                        TreeMap::new));

    }

    public static Map<String, Double> getStats(Map<String, Long> map) {

        double sum = map
                .values()
                .stream()
                .mapToDouble(Long::doubleValue)
                .reduce(0, Double::sum);

        return map.entrySet()
                .stream()


                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().doubleValue() / sum,
                        Double::sum,
                        TreeMap::new
                ));

    }

    public static String decipherText(String s, Map<String, String> mapper) {

        return s.chars()
                .mapToObj(Character::toString)
                .map(ch -> {

                    if (mapper.containsKey(ch)) {
                        return mapper.get(ch);
                    } else {
                        return ch;
                    }
                })
                .collect(Collectors.joining());

    }

    public static String shiftCipher(String cipherString, int shiftCount) {

        // require check on negative numbers to convert it to positive and re-use the same logic
        // e.g. -4 mod 26 becomes 22
        // due Java's inconsistent modulus implementation
        int shift = shiftCount >= 0 ? shiftCount : (shiftCount % 26 + 26);
        System.out.println(shift);
        return cipherString.chars()
                .peek(System.out::println)
                .map(i -> ((i - 97) + shift) % 26 + 97)
                .peek(System.out::println)
                .mapToObj(i -> (char) i)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
    }

    public static String decriptAffineCipher(String s, int a, int b) {

        OptionalInt inverseMultiplier = getInverseMultiplier(a, 26);

        if (inverseMultiplier.isPresent()) {

            // encrypt affine cipher y = x * a + b mod 26;
            // decript affine cipher x = (y - b) a ^ (-1) mod 26;
            return s.chars()
                    .map(y -> {
                        return Math.floorMod((y - 97 - b) * 15, 26) + 97;
                    })
                    .mapToObj(ch -> (char) ch)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
        } else {
            return s;
        }
    }

    public static OptionalInt getInverseMultiplier(int a, int modulus) {

        if (GCD.gcd(a, modulus) != 1) {
            return OptionalInt.empty();
        }

        return IntStream.range(1, modulus)
                .filter(i -> Math.floorMod(a * i, modulus) == 1)
                .findFirst();
    }
}
