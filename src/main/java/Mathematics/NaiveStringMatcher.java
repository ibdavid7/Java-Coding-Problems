package Mathematics;

public class NaiveStringMatcher {


    public static void main(String[] args) {
        String text = "eceyeye";
        String pattern = "eye";

        naiveStringMatcher(text, pattern);
    }

    public static void naiveStringMatcher(String text, String pattern) {

        if (pattern.length() > text.length()) return;

        for (int i = 0; i <= text.length() - pattern.length(); i++) {

            int j = 0;

            while (j < pattern.length() && pattern.charAt(j) == text.charAt(i + j)) {
                j++;
            }

            if (j == pattern.length()) System.out.printf("%d is a valid shift\n", i);

        }

    }

}
