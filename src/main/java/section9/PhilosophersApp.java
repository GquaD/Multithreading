package section9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhilosophersApp {
    //The aim of the simulation is that it iis possible to avoid thread starvation:
    // - all the threads are going to be executed by the executor service
    // - that we able to avoid deadlocks because we use tryLock()

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        Philosopher[] arrPhil = null;
        Chopstick[] chopsticks = null;

        try {
            arrPhil = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];

            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++)
                chopsticks[i] = new Chopstick(i);

            service = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                arrPhil[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                service.execute(arrPhil[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
            //service.shutdown();

            for (Philosopher p : arrPhil) {
                p.setFull(true);
            }
        } finally {
            service.shutdown();
            while (!service.isTerminated())
                Thread.sleep(1000);

            for (Philosopher p : arrPhil) {
                System.out.println(p + " ate #" + p.getEatingCounter() + " times");
            }
        }
    }
}
