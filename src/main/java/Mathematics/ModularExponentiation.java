package Mathematics;

public class ModularExponentiation {

    public static void main(String[] args) {
        long b = 3;
        long exp = 644;
        long mod = 645;
        System.out.println(moduleExponentiation(b, exp, mod));

    }

    public static long moduleExponentiation(long b, long exponent, long modulus) {


        if (b < 0 || exponent < 0) {
            new IllegalArgumentException("b and exponent cannot be less than 0");
        }
        if (b == 0) {
            return 0L;
        } else if (exponent == 0 || b == 1) {
            return 1L;
        } else if (exponent == 1) {
            return b;
        } else {

            long res = 1;
            long q = exponent;
            long r;
            long power = Math.floorMod(b, modulus);

            while (q > 0) {
                q = q >> 1;
                r = q % 2;
                power = Math.floorMod(power * power, modulus);
                if (r == 1) {
                    res = Math.floorMod(res * power, modulus);
                }
//                System.out.println("Res:" + res + ", power:" + power);

            }

            return res;

        }


    }


}
