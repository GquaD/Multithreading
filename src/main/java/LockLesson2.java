import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");
        //similar to wait()
        condition.await();
        System.out.println("Again producer method...");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        //we want to make sure that we start with producer
        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consumer method...");
        Thread.sleep(3000);
        //similar to notify
        condition.signal();
        lock.unlock();
    }
}

public class LockLesson2 {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(() ->{
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() ->{
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(counter);
    }
}
