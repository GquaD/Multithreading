package section7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        //it is a single thread that will execute tasks sequentially
        //one after another
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task(i));
        }

        //the process will not be terminated even after all threads will be executed
        //we need to shut down the executor service
    }
}
