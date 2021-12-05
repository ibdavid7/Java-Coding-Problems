package JetBrainsAcademy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class ConcurrentMaps {

    public static void main(String[] args) throws InterruptedException {

/*        Thread syncMap = new Thread(() -> {
            try {
                synchronizedMap();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread concMap = new Thread(() -> {
            try {
                concurrentMap();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        syncMap.start();
        concMap.start();*/

        synchronizedMap();
        concurrentMap();
        ordinaryMap();
    }

    public static void ordinaryMap() throws InterruptedException {
        long timeStart = System.currentTimeMillis();

        Map<Integer, String> ordinaryMap = new HashMap<>();

        Thread writer1 = new Thread(() -> addPositiveInts(ordinaryMap));
        Thread writer2 = new Thread(() -> addNegativeInts(ordinaryMap));

        writer1.start();
        writer2.start();


        System.out.println("Ord Threads: " + Thread.currentThread().getName());
        System.out.println("Ord Threads count: " + Thread.activeCount());

        writer1.join();
        writer2.join();

        long timeFinish = System.currentTimeMillis();
        System.out.println("--------------------------");
        System.out.println("Ordinary Map Performance");
        System.out.println("Map size: " + ordinaryMap.size());
        System.out.println("Time taken in milis: " + (timeFinish - timeStart));
        System.out.println("--------------------------");
    }

    public static void concurrentMap() throws InterruptedException {
        long timeStart = System.currentTimeMillis();

        Map<Integer, String> concurrentMap = new ConcurrentHashMap<>();

        Thread writer1 = new Thread(() -> addPositiveInts(concurrentMap));
        Thread writer2 = new Thread(() -> addNegativeInts(concurrentMap));

        writer1.start();
        writer2.start();


        System.out.println("Concur Threads: " + Thread.currentThread().getName());
        System.out.println("Concur Threads count: " + Thread.activeCount());

        writer1.join();
        writer2.join();

        long timeFinish = System.currentTimeMillis();
        System.out.println("--------------------------");
        System.out.println("Concurrent Map Performance");
        System.out.println("Map size: " + concurrentMap.size());
        System.out.println("Time taken in milis: " + (timeFinish - timeStart));
        System.out.println("--------------------------");
    }

    public static void synchronizedMap() throws InterruptedException {

        long timeStart = System.currentTimeMillis();

        Map<Integer, String> ordMap = new HashMap<>();
        Map<Integer, String> syncMap = Collections.synchronizedMap(ordMap);

        Thread writer1 = new Thread(() -> addPositiveInts(syncMap));
        Thread writer2 = new Thread(() -> addNegativeInts(syncMap));

        writer1.start();
        writer2.start();

        System.out.println("Sync Threads: " + Thread.currentThread().getName());
        System.out.println("Sync Threads count: " + Thread.activeCount());

        writer1.join();
        writer2.join();

        long timeFinish = System.currentTimeMillis();
        System.out.println("--------------------------");
        System.out.println("Synchronized Map Performance");
        System.out.println("Map size: " + syncMap.size());
        System.out.println("Time taken in milis: " + (timeFinish - timeStart));
        System.out.println("--------------------------");


    }

    public static void addPositiveInts(Map<Integer, String> map) {

        System.out.println("Pos Method Threads: " + Thread.currentThread().getName());

        IntStream.range(0, 10_000_000)
                .parallel()
                .forEach(i -> {
                    map.put(i, "Integer is: " + i);
                });

    }

    public static void addNegativeInts(Map<Integer, String> map) {

        System.out.println("Neg Method Threads: " + Thread.currentThread().getName());

        IntStream.range(-10_000_000, 0)
                .parallel()
                .forEach(i -> {
                    map.put(i, "Integer is: " + i);
                });
    }

}
