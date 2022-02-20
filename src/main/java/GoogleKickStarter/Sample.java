package GoogleKickStarter;

//2022

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Sample {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = in.nextInt(); // Test cases
        for (int i = 1; i <= t; ++i) {
            int bags = in.nextInt();
            int kids = in.nextInt();

            int sum = 0;
            for (int j = 0; j < bags; j++) {
                sum += in.nextInt();
            }


            System.out.println("Case #" + i + ": " + sum % kids);

        }

    }

}
