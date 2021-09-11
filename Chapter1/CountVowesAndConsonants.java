import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CountVowesAndConsonants {

    public static void main(String[] args) {
        String s1 = "alibaba1?";
        String s2 = "AliBABA1?$%";

        System.out.println(countVowesAndConsonants(s1));
        System.out.println(countVowesAndConsonants(s2));

    }

    public static Map<String, Long> countVowesAndConsonants(String s) {

        return s.codePoints()
                .mapToObj(i -> String.valueOf(Character.toChars(i)))
                .collect(Collectors.groupingBy(
                        CountVowesAndConsonants::labelChar,
                        HashMap::new,
                        Collectors.counting()));

    }

    public static String labelChar(String s) {

        Pattern vowel = Pattern.compile("[aeiou]", Pattern.CASE_INSENSITIVE);
        Pattern consonant = Pattern.compile("([a-z&&[^aeiou]])", Pattern.CASE_INSENSITIVE);

        if (vowel.matcher(s).matches()) {
            return "vowel";
        } else if (consonant.matcher(s).matches()) {
            return "consonant";
        } else {
            return "other";
        }

    }

}
