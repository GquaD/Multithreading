class Process {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running the produce() method...");
            this.wait();
            System.out.println("Again in the produce() method...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consume() method is executed...");
            notify();
            //it is not going to handle the lock immediately: we can make further operations
            //until the block ends
            Thread.sleep(2000);
            System.out.println("Consume() after notify...");
        }
    }
}

public class App2 {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
