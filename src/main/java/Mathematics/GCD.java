package main.java.Mathematics;

import java.util.Scanner;

public class GCD {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int[] input = getInput(in);

        System.out.println(gcd(input[0], input[1]));

    }

    private static int[] getInput(Scanner in) {

        System.out.println("Enter 1st Integer:");
        int i1 = in.nextInt();
        System.out.println("Enter 1st Integer:");
        int i2 = in.nextInt();
        return new int[]{i1, i2};
    }

    public static int gcd(int i1, int i2) {

        // base case
        if (i2 == 0) {
            return i1;
        }
        // recursive case
        System.out.println(i1 + " " + i2);
        return gcd(i2, i1 % i2);

    }

}
