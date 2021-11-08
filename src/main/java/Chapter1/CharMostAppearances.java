import java.nio.CharBuffer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharMostAppearances {

    public static void main(String[] args) {

        System.out.println(findMostFrequentChar("beee"));

    }

    public static char findMostFrequentChar(String s) {

        Map<Integer, Long> hashMap2 = s.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int i2 = hashMap2.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return (char) i2;

    }

    public static char findMostFrequentChar2(String s) {

        Map<Character, Long> hashMap = new HashMap<>();

        s.chars()
                .forEach(i -> hashMap.compute((char) i, (k, v) -> (v == null) ? 1 : v + 1));

        return hashMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

    }

}
