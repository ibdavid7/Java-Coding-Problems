package main.java.Mathmatics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class GoldbachConjecture {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(calculate(26587002L)));

    }

    private static final List<Long> primeList = new ArrayList<>();

    private static boolean isPrime(Long l) {

        if (l == 2) return true;

        if (l % 2 == 0 || l == 1) return false;

        return LongStream.iterate(3, n -> n < Math.sqrt(l), n -> n = n + 2)
//                .peek(System.out::println)
                .parallel()
                .noneMatch(n -> l % n == 0);

    }

    private static void memoizePrimes(Long l) {

        if (l < 3 || l % 2 != 0) throw new IllegalArgumentException("Input must be greater EVEN than 2");

        primeList.add(2L);

        LongStream.iterate(3, n -> n < l, n -> n + 2)
//                .peek(n -> System.out.println(n + "  " + isPrime(n)))
                .filter(GoldbachConjecture::isPrime)
                .forEach(primeList::add);

    }

    private static Long[] combinations(Long l) {

        for (int i = 0; i < primeList.size(); i++) {

            for (int j = i; j < primeList.size(); j++) {

                if (primeList.get(i) + primeList.get(j) == l)

                    return new Long[]{primeList.get(i), primeList.get(j)};
            }
        }
/*        IntStream.iterate(0, n -> n < primeList.size(), n -> ++n)
                .forEach(n -> {

                    IntStream.range(n, primeList.size())
                            .forEach(k -> {

                                if (primeList.get(n) + primeList.get(k) == l) {
                                    pair[0] = primeList.get(n);
                                    pair[1] = primeList.get(k);
                                    return pair;
                                }

                            });
                });*/
        return new Long[]{null, null};
    }

    public static Long[] calculate(Long l) {

        memoizePrimes(l);
//        System.out.println(primeList);
        return combinations(l);

    }

}
