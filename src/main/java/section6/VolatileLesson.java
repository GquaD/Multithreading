package section6;

class Worker implements Runnable {

    //CPU is not gonna cache this variable (means that thread will not have its own copy of the variable)
    private volatile boolean terminated;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Worker class is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

public class VolatileLesson {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        worker.setTerminated(true);
        System.out.println("Algorithm is terminated...");
    }
}
