package section8;

import java.util.concurrent.PriorityBlockingQueue;

class FirstWorkerPQ implements Runnable {

    private PriorityBlockingQueue<String> queue;

    public FirstWorkerPQ(PriorityBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put("B");
            queue.put("H");
            queue.put("F");
            Thread.sleep(2000);
            queue.put("A");
            Thread.sleep(1000);
            queue.put("Z");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondWorkerPQ implements Runnable {

    private PriorityBlockingQueue<String> queue;

    public SecondWorkerPQ(PriorityBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("Take: " + queue.take());
            Thread.sleep(1000);
            System.out.println("Take: " + queue.take());
            Thread.sleep(2000);
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>();

        FirstWorkerPQ firstWorkerPQ = new FirstWorkerPQ(queue);
        SecondWorkerPQ secondWorkerPQ = new SecondWorkerPQ(queue);

        new Thread(firstWorkerPQ).start();
        new Thread(secondWorkerPQ).start();


    }


}
