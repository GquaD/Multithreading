package section9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philosopher, StateOfChopstick state) throws InterruptedException {
        //this is where we will simulate deadlock

        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picks up " + this + " " + state.toString());
            return true;
        }

        return false;
    }

    public void putDown(Philosopher philosopher, StateOfChopstick right) {
        lock.unlock();
        System.out.println(philosopher + " puts down " + this);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}
