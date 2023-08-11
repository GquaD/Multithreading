package section14;

import java.util.stream.LongStream;

public class App3 {
    public static void main(String[] args) {

        //parallel() - because we have to make sure that the given stream
        //can be parallelized
        //under the hood the fork-join framework is used
        long n = 2_000_000_000L;

        long start = System.currentTimeMillis();
        System.out.println(sum(n));
        System.out.println("Sequential time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(parallelSum(n));
        System.out.println("Parallel time: " + (System.currentTimeMillis() - start));
    }

    private static long sum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    private static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }
}
