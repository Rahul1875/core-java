package src.com.pack.thread;

import java.util.LinkedList;

public class ProducerConsumerProblem {

    public static void main(String[] args) throws InterruptedException {
        PC a1 = new PC();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a1.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a1.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }


    public static class PC {
        int MAX = 2;
        LinkedList<Integer> l1 = new LinkedList<>();
        public void producer() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (this) {
                    while (l1.size() > MAX) {
                        wait();
                    }
                    System.out.println("Producer produced-" + value);
                    l1.add(value++);
                    notify();
                    Thread.sleep(2000);
                }
            }
        }

        public void consumer () throws InterruptedException {
            while (true) {
                synchronized (this) {
                    while (l1.isEmpty()) {
                        wait();
                    }
                    int x = l1.removeFirst();
                    System.out.println("consumer consumer: " + x);
                    notify();
                    Thread.sleep(2000);
                }

            }
        }

    }

}
