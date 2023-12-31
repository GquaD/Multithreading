import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private int value = 0;

    private final Object lock = new Object();

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("Waiting for removing items...");
                    lock.wait();
                } else {
                    System.out.println("Adding: + " + value);
                    list.add(value);
                    value++;
                    //can call the notify() - because the other thread will be notified
                    //only when iti is in a waiting state
                    lock.notify();
                    //java not going to notify() immediately
                    //it does operations until wait() triggered
                    //and then notifies of block which is waiting for the lock
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_LIMIT) {
                    System.out.println("Waiting for adding items...");
                    lock.wait();
                } else {
                    System.out.println("Removing: + " + list.remove(list.size() - 1));
                    value--;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}

public class App3 {
    public static void main(String[] args) {
        Processor process = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                process.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                process.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
