import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveDuplicateCharacters {

    public static void main(String[] args) {
        String s1 = "112345";

        System.out.println(toHashMap(s1));

        System.out.println(removeDuplicateChars(s1, toHashMap(s1)));
        System.out.println(removeDuplicateChars2(s1));
        System.out.println(removeDuplicateChars3(s1));
        System.out.println(removeDuplicateChars4(s1));

    }


    public static HashMap<Integer, Long> toHashMap(String s) {

        return s.codePoints()
                .collect(HashMap::new, (map, i) -> map.put(i, map.getOrDefault(i, 0L) + 1L), HashMap::putAll);

    }

    public static String removeDuplicateChars(String s, Map<Integer, Long> map) {

        AtomicReference<String> s1 = new AtomicReference<>(s);

        map.entrySet().stream()
                .filter(integerLongEntry -> integerLongEntry.getValue() > 1)
                .forEach(integerLongEntry -> {
                    char[] ch=Character.toChars(integerLongEntry.getKey());

                    System.out.println(ch);
                    System.out.println(String.valueOf(ch));
//                    s1.get().replaceAll(String.valueOf(ch), "");
                    s1.getAndSet(s1.get().replaceAll(String.valueOf(ch), ""));
                });

        return s1.get();
    }

    public static String removeDuplicateChars2(String s) {

        return s.codePoints()
                .distinct()
                .mapToObj(i -> String.valueOf(Character.toChars(i)))
                .collect(Collectors.joining());

    }

    public static String removeDuplicateChars3(String s) {

        char[] chars = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char ch : chars) {
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }

        return sb.toString();

    }

    public static String removeDuplicateChars4(String s) {

        StringBuilder sb = new StringBuilder();

        s.codePoints()
                .distinct()
                .forEach(cp -> sb.appendCodePoint(cp));
        return sb.toString();

    }
}
