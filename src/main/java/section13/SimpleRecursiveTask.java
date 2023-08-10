package section13;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Double> {

    private double num;


    public SimpleRecursiveTask(double num) {
        this.num = num;
    }

    @Override
    protected Double compute() {
        if (num > 100) {
            // parallelisation: we split the problem into 2 subproblems
            System.out.println("Parallel execution so split the task..." + num);

            SimpleRecursiveTask task1 = new SimpleRecursiveTask(num / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(num / 2);

            task1.fork();
            task2.fork();

            double subSolution = 0;
            subSolution += task1.join();
            subSolution += task2.join();

            return subSolution;
        } else {
            //the problem is too small - we can use sequential approach
            System.out.println("The task is small... we can execute it sequentially");
            return 2 * num;
        }
    }
}
