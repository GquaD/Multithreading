package section14;

import java.util.stream.IntStream;

public class AppPrimes {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //sequential stream
        long numOfPrimes = IntStream.range(2, Integer.MAX_VALUE / 100).filter(AppPrimes::isPrime).count();
        System.out.println("Number of primes (sequential): " + numOfPrimes);
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));

        //parallel stream
        start = System.currentTimeMillis();
        numOfPrimes = IntStream.range(2, Integer.MAX_VALUE / 100).parallel().filter(AppPrimes::isPrime).count();
        System.out.println("Number of primes (parallel): " + numOfPrimes);
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
    }

    public static boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        //we can check the numbers in the range [0, sqrt(N)]

        long maxDivisor = (long) Math.sqrt(num);
        for (int i = 3; i <= maxDivisor; i += 2) if (num % i == 0) return false;

        return true;
    }
}
