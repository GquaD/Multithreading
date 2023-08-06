package section12;

public class ParallelSum {
    private ParallelSumWorker[] workers;
    private int numOfThreads;

    public ParallelSum(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.workers = new ParallelSumWorker[numOfThreads];
    }

    public int sum(int[] nums) {
        int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);
        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new ParallelSumWorker(nums, i * size, (i + 1) * size);
            workers[i].start();
        }

        try {
            for (ParallelSumWorker worker : workers) worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int total = 0;
        for (ParallelSumWorker worker : workers) total += worker.getPartialSum();

        return total;
    }
}
