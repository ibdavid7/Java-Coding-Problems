package GoogleKickStarter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class CentauriPrime {


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        char[] vowels = new char[]{'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};

        int t = in.nextInt(); // Test cases
        for (int i = 1; i <= t; ++i) {
            String kingdomName = in.next();

            char lastChar = kingdomName.toLowerCase().charAt(kingdomName.length() - 1);
//            System.out.println(lastChar);
            String ruler;

            if (Arrays.binarySearch(vowels, lastChar) >= 0) {
                ruler = "Alice";
            } else if (lastChar == 'y') {
                ruler = "nobody";
            } else {
                ruler = "Bob";
            }


            System.out.printf("Case #%d: %s is ruled by %s.\n", i, kingdomName, ruler);


        }

    }


}
