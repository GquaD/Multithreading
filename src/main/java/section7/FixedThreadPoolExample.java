package section7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        //it is a single thread that will execute tasks sequentially
        //one after another
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task(i + 1));
        }

        //the process will not be terminated even after all threads will be executed
        //we need to shut down the executor service

        //we prevent the executor to execute any further tasks
        executorService.shutdown();

        //terminate running tasks
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                //shutdownNow() shuts down quickly, not letting all tasks to finish
                //executorService.shutdownNow();

                //if we comment out it ^, then we let all tasks finish and shut down the process.

            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

    }
}
