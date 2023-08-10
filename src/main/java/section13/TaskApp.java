package section13;

import java.util.concurrent.ForkJoinPool;

public class TaskApp {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        /*SimpleRecursiveTask task = new SimpleRecursiveTask(10000);
        System.out.println(pool.invoke(task));*/

        FibonacciRecursiveTask task = new FibonacciRecursiveTask(22);
        System.out.println(pool.invoke(task));

    }
}
