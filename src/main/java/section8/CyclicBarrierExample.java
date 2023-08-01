package section8;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerBarrier implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public WorkerBarrier(int id, CyclicBarrier barrier) {
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id: " + id + " starts the work...");

        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //when all the threads call await() 5 times
            //when "barrier is broken"
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("After the await() for thread with id: " + id);
    }
}

public class CyclicBarrierExample {

    public static void main(String[] args) {
        int num = 5;
        ExecutorService service = Executors.newFixedThreadPool(num);
        //this lambda function is executed after all threads call await()
        CyclicBarrier barrier = new CyclicBarrier(num, () -> System.out.println("All tasks have been finished..."));

        for (int i = 0; i < num; i++) {
            service.execute(new WorkerBarrier(i + 1, barrier));
        }
        service.shutdown();
    }


}
