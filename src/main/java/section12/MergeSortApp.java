package section12;

import java.util.Arrays;
import java.util.Random;

public class MergeSortApp {
    public static void main(String[] args) {
        /*int[] nums = {5, -1, 0, 7, 2, 3, 2, 1, 0, 1, 2};
        MergeSort mergeSort = new MergeSort(nums);
        mergeSort.sort();
        mergeSort.showArray();*/

        int numOfThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numOfThreads + " threads are available");

        int[] arr1 = createArray(10_000_000);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        //parallel 215ms
        ParallelMergeSort parallelMergeSorter = new ParallelMergeSort(arr1);
        long startTime = System.currentTimeMillis();
        parallelMergeSorter.parallelMergeSort(0, arr1.length - 1, numOfThreads);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with parallel algorithm: " + (endTime - startTime) + "ms");

        //sequential 1021ms
        MergeSort mergeSort = new MergeSort(arr2);
        long startTime1 = System.currentTimeMillis();
        mergeSort.sort();
        long endTime1 = System.currentTimeMillis();
        System.out.println("Time taken with sequential algorithm: " + (endTime1 - startTime1) + "ms");

    }

    private static int[] createArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(n);

        return arr;
    }
}
