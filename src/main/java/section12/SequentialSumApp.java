package section12;

import java.util.Random;

public class SequentialSumApp {
    public static void main(String[] args) {

        Random random = new Random();
        int numOfElements = 100_000_000;
        int[] nums = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++)
            nums[i] = random.nextInt(100);

        //sequential algorithm
        SequentialSum sequentialSum = new SequentialSum();
        long start = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sequentialSum.sum(nums));
        System.out.println("Time: " + (System.currentTimeMillis() - start));

        //parallel algorithm
        int n = Runtime.getRuntime().availableProcessors();
        ParallelSum parallelSum = new ParallelSum(n);
        start = System.currentTimeMillis();
        System.out.println("Parallel sum: " + parallelSum.sum(nums));
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}
