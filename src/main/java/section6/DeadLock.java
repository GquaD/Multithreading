package section6;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquires the lock1...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock2.lock();
        System.out.println("Worker1 acquires the lock2...");
        lock1.unlock();
        lock2.unlock();
    }
    public void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquires the lock2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock1.lock();
        System.out.println("Worker2 acquires the lock1...");
        lock2.unlock();
        lock1.unlock();
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::worker1, "worker1").start();
        new Thread(deadLock::worker2, "worker2").start();
    }
}
