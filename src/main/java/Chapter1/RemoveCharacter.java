import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveCharacter {

    public static void main(String[] args) {
        System.out.println(removeChar("Hello World", 'e'));
    }

    public static String removeChar(String s, char ch) {
        return CharBuffer.wrap(s.toCharArray())
                .chars()
                .filter(i -> i != (int) ch)
                .mapToObj(i -> (char) i)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();

    }

}
