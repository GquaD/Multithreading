package section12;

public class ParallelSumWorker extends Thread {

    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelSumWorker(int[] nums, int low, int high) {
        super();
        this.nums = nums;
        this.low = low;
        this.high = Math.min(high, nums.length);
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i = low; i < high; i++) partialSum += nums[i];
    }

    public int getPartialSum() {
        return partialSum;
    }
}
