package section12;

import java.util.Arrays;

public class ParallelMergeSortApp {
    private int[] array;

    public void mergeSort(int[] arr) {
        array = arr;
        int len = array.length;
        int[] workspace = new int[len];
        recursiveMergeSort(workspace, 0, len - 1);
    }

/*    public void parallelMergeSort(int low, int high, int numOfThreads, int[] arr) {
        if (numOfThreads <= 1) {
            mergeSort(low, high, arr);
        }
    }

    private Thread createThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }*/
    private void recursiveMergeSort(int[] workSpace, int lowerBound,
                                    int upperBound) {
        if (lowerBound == upperBound) {
            return;
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recursiveMergeSort(workSpace, lowerBound, mid);
            recursiveMergeSort(workSpace, mid + 1, upperBound);
            merge(workSpace, lowerBound, mid + 1, upperBound);
        }
    }

    private void merge(int[] workspace, int lowPointer,
                       int highPointer, int upperBound) {
        int i = 0;
        int lowerBound = lowPointer;
        int mid = highPointer - 1;
        int numberOfItems = upperBound - lowerBound + 1;

        while (lowPointer <= mid && highPointer <= upperBound) {
            if (array[lowPointer] < array[highPointer]) {
                workspace[i++] = array[lowPointer++];
            } else {
                workspace[i++] = array[highPointer++];
            }
        }

        while (lowPointer <= mid)
            workspace[i++] = array[lowPointer++];

        while (highPointer <= upperBound)
            workspace[i++] = array[highPointer++];

        for (i = 0; i < numberOfItems; i++)
            array[lowerBound + i] = workspace[i];
    }


    public static void main(String[] args) {
        ParallelMergeSortApp mergeSort = new ParallelMergeSortApp();
        int[] arr = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("Before: " + Arrays.toString(arr));
        mergeSort.mergeSort(arr);
        System.out.println("After: " + Arrays.toString(arr));
    }
}

