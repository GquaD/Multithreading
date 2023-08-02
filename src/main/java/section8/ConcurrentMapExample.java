package section8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class MapWorker1 implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapWorker1(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z", 5);
            map.put("A", 25);
            Thread.sleep(2000);
            map.put("D", 19);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class MapWorker2 implements Runnable {
    private ConcurrentMap<String, Integer> map;

    public MapWorker2(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(2000);
            System.out.println(map.get("Z"));
            System.out.println(map.get("B"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ConcurrentMapExample {

    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        MapWorker1 worker1 = new MapWorker1(map);
        MapWorker2 worker2 = new MapWorker2(map);

        new Thread(worker1).start();
        new Thread(worker2).start();
    }
}
