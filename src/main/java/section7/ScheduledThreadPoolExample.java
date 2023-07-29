package section7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {
    @Override
    public void run() {
        System.out.println("Updating and downloading stock related data from the web...");

    }
}

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new StockMarketUpdater(), 1000, 2000, TimeUnit.MILLISECONDS);
        //calls every 2 seconds
        //we can control the frequency the thread run
    }
}
