package section13;

import java.util.concurrent.RecursiveTask;

public class ParallelMaxFindingTask extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIdx;
    private int highIdx;

    public ParallelMaxFindingTask(long[] nums, int lowIdx, int highIdx) {
        this.nums = nums;
        this.lowIdx = lowIdx;
        this.highIdx = highIdx;
    }

    @Override
    protected Long compute() {
        if (highIdx - lowIdx < 5000) {
            return sequentialMaxFinding();
        } else {
            //we have to use parallelization
            int midIdx = (highIdx + lowIdx) / 2;
            ParallelMaxFindingTask task1 = new ParallelMaxFindingTask(nums, lowIdx, midIdx);
            ParallelMaxFindingTask task2 = new ParallelMaxFindingTask(nums, midIdx + 1, highIdx);

            invokeAll(task1, task2);

//            return Math.max(task1.compute(), task2.join());
            return Math.max(task1.join(), task2.join());
        }

    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIdx];
        for (int i = lowIdx + 1; i < highIdx; i++) max = Math.max(max, nums[i]);
        return max;
    }
}
