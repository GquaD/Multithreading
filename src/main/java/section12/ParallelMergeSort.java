package section12;

public class ParallelMergeSort {
    private int[] nums;
    private int[] tempArr;

    public ParallelMergeSort(int[] nums) {
        this.nums = nums;
        tempArr = new int[nums.length];
    }

    public void sort() {
        mergeSort(0, nums.length - 1);
    }

    public void parallelMergeSort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int mid = (low + high) / 2;

        Thread leftSorter = createThread(low, mid, numOfThreads);
        Thread rightSorter = createThread(mid + 1, high, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, mid, high);
    }

    private Thread createThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }

    //divide and conquer approach
    private void mergeSort(int low, int high) {
        if (low >= high) return;

        int midIdx = (low + high) / 2;

        mergeSort(low, midIdx);
        mergeSort(midIdx + 1, high);
        merge(low, midIdx, high);
    }

    private void merge(int low, int midIdx, int high) {
        for (int i = low; i <= high; i++) tempArr[i] = nums[i];

        int i = low;
        int j = midIdx + 1;
        int k = low;
        while (i <= midIdx && j <= high) {
            if (tempArr[i] < tempArr[j]) {
                nums[k] = tempArr[i];
                i++;
            } else {
                nums[k] = tempArr[j];
                j++;
            }
            k++;
        }

        while (i <= midIdx) {
            nums[k] = tempArr[i];
            k++;
            i++;
        }

        while (j <= midIdx) {
            nums[k] = tempArr[j];
            k++;
            j++;
        }
    }

    public void showArray() {
        for (int i = 0; i < nums.length; i++) System.out.print(nums[i] + " ");
    }

    private void swap(int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
