import java.util.Locale;
import java.util.stream.IntStream;

public class IsPalindrome {

    public static void main(String[] args) {
        String s1 = "Tenet";
        String s2 = "madam";
        String s3 = "nUrses Run";
        String s4 = "maam";
        String s5 = "maams";

        System.out.println(isPalindrome(s1));
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome(s3));
        System.out.println(isPalindrome(s4));
        System.out.println(isPalindrome(s5));

        System.out.println(isPalindromeFunctional(s1));
        System.out.println(isPalindromeFunctional(s2));
        System.out.println(isPalindromeFunctional(s3));
        System.out.println(isPalindromeFunctional(s4));
        System.out.println(isPalindromeFunctional(s5));
    }

    public static boolean isPalindrome(String s) {
        String temp = s.toLowerCase(Locale.ROOT).replaceAll(" ", "");

        int midPoint = temp.length() / 2;
        int length = temp.length();

        for (int i = 0; i < midPoint; i++) {
            if (temp.charAt(i) != temp.charAt(length - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindromeFunctional(String s) {

        String temp = s.toLowerCase(Locale.ROOT).replaceAll(" ", "");

        return IntStream.range(0, temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length() - 1 - i));

    }

}
