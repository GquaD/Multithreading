package section13;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class MaxTaskApp {
    public static void main(String[] args) {

        long[] nums = createNumbers(600_000_000);

        //sequential order
        SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
        long start = System.currentTimeMillis();
        System.out.println("Sequential Max: " + sequentialMaxFinding.max(nums));
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - start) + "\n");

        //parallel order
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxFindingTask maxFindingTask = new ParallelMaxFindingTask(nums, 0, nums.length);
        start = System.currentTimeMillis();
        System.out.println("Parallel Max: " + pool.invoke(maxFindingTask));
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - start));
    }

    private static long[] createNumbers(int n) {
        Random random = new Random();
        long[] nums = new long[n];
        for (int i = 0; i < nums.length; i++) nums[i] = random.nextInt(1000);
        return nums;
    }
}
