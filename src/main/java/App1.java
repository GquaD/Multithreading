public class App1 {

    public static int counter1 = 0;
    public static int counter2 = 0;

    //created my custom objects for locking in the synchronized block
    //increment() methods now are not using current class' (App1) intrinsic lock
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    //have to make sure this method is executed only by a single thread
    //at a given time

    //since App object has a single lock, this is why the method can not
    //be executed "at the same time" - time slicing algorithm

    //usually it is not a good practice to use synchronized keyword
    //for method definition

//    public static synchronized void increment1() {
    public static void increment1() {
        //acquire class level lock
        //rule of thumb: we synchronize blocks that are 100% necessary
        synchronized (lock1) {
            counter1++;
        }
    }
//    public static synchronized void increment2() {
    public static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }
    public static void process() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) increment1();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) increment2();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("The counter1 is: " + counter1);
        System.out.println("The counter2 is: " + counter2);
    }

    public static void main(String[] args) {
        process();
    }
}
