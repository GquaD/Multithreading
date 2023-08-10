package section13;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;
    public SimpleRecursiveAction(int simWork) {
        this.simulatedWork = simWork;
    }

    @Override
    protected void compute() {
        //if the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split the tasks...");

            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork / 2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork / 2);

            action1.fork();
            action2.fork();

            action1.join();
            action2.join();
            //or use
//            invokeAll(action1, action2);
        } else {
            System.out.println("The task is rather small so sequential execution is fine...");
            System.out.println("Size of the task: " + simulatedWork);
        }
    }
}
