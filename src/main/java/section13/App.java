package section13;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        //Runtime.getRuntime().availableProcessors() is default value for forkJoinPool

        ForkJoinPool pool = new ForkJoinPool();
        /*SimpleRecursiveAction action = new SimpleRecursiveAction(500);
        action.invoke();*/

        PrintIntegersRecursiveAction action = new PrintIntegersRecursiveAction(Arrays.asList(1,2,3,4,5,6,7,8));
        action.invoke();
    }
}
