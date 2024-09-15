package src.com.pack.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreeThreadsOrderedLockLess {
    AtomicInteger sharedOutput = new AtomicInteger(0);

    public static void main(String args[]) {
        ThreeThreadsOrderedLockLess t = new ThreeThreadsOrderedLockLess();

        ThreadTasks t1 = t.new ThreadTasks(0);
        ThreadTasks t2 = t.new ThreadTasks(1);
        ThreadTasks t3 = t.new ThreadTasks(2);

        Thread ts1 = new Thread(t1);
        ts1.setName("first");
        Thread ts2 = new Thread(t2);
        ts1.setName("second");
        Thread ts3 = new Thread(t3);
        ts1.setName("third");
        ts1.start();
        ts2.start();
        ts3.start();
    }

    private class ThreadTasks implements Runnable {
        private final int threadPosition;
        public ThreadTasks(int threadPosition) {
            super();
            this.threadPosition = threadPosition;
        }

        @Override
        public void run() {
            while (sharedOutput.get() < 9) {
                synchronized (sharedOutput) {
                    int value = sharedOutput.get() + 1;
                    if (sharedOutput.get() % 3 == this.threadPosition) {
                    //if (sharedOutput.get() % 2 == this.threadPosition) {
                        System.out.println("Printing output for Thread: "
                                + Thread.currentThread().getName() + " : "
                                //+ this.threadPosition + "  "
                                + value);
                        sharedOutput.incrementAndGet();
                    }
                }
            }
        }
    }

}