package section9;

import java.util.Random;

public class Philosopher implements Runnable {

    private int id;
    private volatile boolean isFull;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            //after eating 1000 then we will terminate
            while (!isFull) {

                think();
                if (leftChopstick.pickUp(this, StateOfChopstick.LEFT)) {
                    //left is available
                    if (rightChopstick.pickUp(this, StateOfChopstick.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, StateOfChopstick.RIGHT);
                    }
                    leftChopstick.putDown(this, StateOfChopstick.LEFT);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
