package Mathematics;

import java.math.BigDecimal;
import java.util.Arrays;

public class CashiersAlgorithm {

    public static void main(String[] args) {

        double[] denominations = {0.25, 0.1, 0.05, 0.01};

        double value = 0.16;

        System.out.println(Arrays.toString(cashierAlgo(denominations, value)));

    }

    public static int[] cashierAlgo(double[] denominations, double value) {

        BigDecimal valueBigDecimal = BigDecimal.valueOf(value);
        BigDecimal[] denominationsBigDecimal = new BigDecimal[denominations.length];

        for (int i = 0; i < denominations.length; i++) {
            denominationsBigDecimal[i] = BigDecimal.valueOf(denominations[i]);
        }

        int[] coinCount = new int[4];

        int index = 0;

        System.out.println(Arrays.toString(denominationsBigDecimal));

        while (index < denominations.length && valueBigDecimal.compareTo(BigDecimal.ZERO) > 0) {

            while (valueBigDecimal.compareTo(denominationsBigDecimal[index]) >= 0) {
                coinCount[index]++;
                valueBigDecimal = valueBigDecimal.subtract(denominationsBigDecimal[index]);
            }

            index++;

            System.out.println(Arrays.toString(coinCount));
            System.out.println(valueBigDecimal);
//            System.out.println(index);
//            System.out.println(denominations.length);
//            System.out.println(value);

        }

        return coinCount;

    }


}
