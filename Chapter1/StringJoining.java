import java.util.Arrays;
import java.util.stream.Collectors;

public class StringJoining {
    public static void main(String[] args) {

        String res = Arrays.stream(args, 1, args.length)
                                .collect(Collectors.joining(args[0]));
        System.out.println(res);

    }


}
