package JetBrainsAcademy;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class ConcurrentQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> numbers = new ConcurrentLinkedQueue<>();

        Thread thread0 = new Thread(() -> addNumbers(numbers));

        Thread thread1 = new Thread(() -> pollNumbers(numbers));
        Thread thread2 = new Thread(() -> pollNumbers(numbers));

//        addNumbers(numbers);
        thread0.start();
        thread0.join();
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(numbers.size()); // ?
    }

    private static void addNumbers(Queue<Integer> target) {
        for (int i = 0; i < 100_000; i++) {
            target.add(i);
//            Thread.sleep(10);
        }
    }

    private static void pollNumbers(Queue<Integer> target) {
        for (int i = 0; i < 50_000; i++) {

            target.poll();
//            System.out.println(j);
        }
    }
}