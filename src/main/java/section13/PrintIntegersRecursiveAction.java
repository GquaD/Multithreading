package section13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class PrintIntegersRecursiveAction extends RecursiveAction {

    private List<Integer> list;

    public PrintIntegersRecursiveAction(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void compute() {
        if (list.size() < 2) {
            System.out.println(list.size() == 1 ? list.get(0) : -1);
        } else {
            List<Integer> l1 = new ArrayList<>();
            for (int i = 0; i < list.size() / 2; i++) {
                l1.add(list.get(i));
            }
            List<Integer> l2 = new ArrayList<>();
            for (int i = list.size() / 2; i < list.size(); i++) {
                l2.add(list.get(i));
            }
            PrintIntegersRecursiveAction action1 = new PrintIntegersRecursiveAction(l1);
            PrintIntegersRecursiveAction action2 = new PrintIntegersRecursiveAction(l2);

            invokeAll(action1, action2);
        }
    }
}
