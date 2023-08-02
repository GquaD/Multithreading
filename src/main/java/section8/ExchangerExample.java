package section8;

import java.util.concurrent.Exchanger;

class WorkerExchange1 implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;


    public WorkerExchange1(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        this.counter = 0;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("First worker incremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("First worker get the counter: " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WorkerExchange2 implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;


    public WorkerExchange2(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        this.counter = 0;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("Second worker decremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second worker get the counter: " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        WorkerExchange1 workerExchange1 = new WorkerExchange1(exchanger);
        WorkerExchange2 workerExchange2 = new WorkerExchange2(exchanger);

        new Thread(workerExchange1).start();
        new Thread(workerExchange2).start();
    }
}
