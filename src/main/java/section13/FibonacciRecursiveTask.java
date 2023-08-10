package section13;

import java.util.concurrent.RecursiveTask;

public class FibonacciRecursiveTask extends RecursiveTask<Integer> {

    private int n;

    public FibonacciRecursiveTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) return n;

        FibonacciRecursiveTask task1 = new FibonacciRecursiveTask(n - 1);
        FibonacciRecursiveTask task2 = new FibonacciRecursiveTask(n - 2);

        //task1.fork();
        task2.fork();

        int result = 0;
        //current thread executes compute for fib1
        //so that we don't have this thread waiting till 2 threads are done

        // and we create another thread for task2
        result += task1.compute();
        result += task2.join();

        return result;
    }
}
