package src.com.pack.thread;

public class ThreeThreadsInSequence {
    private volatile Integer count = 1;
    private volatile Integer threadIdToRun = 1;
    private Object object = new Object();

    public static void main(String[] args) {
        ThreeThreadsInSequence t = new ThreeThreadsInSequence();
        Thread t1 = new Thread(t.new Printer(1));
        Thread t2 = new Thread(t.new Printer(2));
        Thread t3 = new Thread(t.new Printer(3));
        t1.start();
        t2.start();
        t3.start();
    }

    class Printer implements Runnable {
        private int threadId;
        public Printer(int id) {
            super();
            this.threadId = id;
        }

        @Override
        public void run() {
            try {
                while (count <= 20) {
                    synchronized (object) {
                        if (threadId != threadIdToRun) {
                            object.wait();
                        } else {
                            System.out.println("Thread " + threadId + " printed " + count);
                            count += 1;

                            if (threadId == 1)
                                threadIdToRun = 2;
                            else if (threadId == 2)
                                threadIdToRun = 3;
                            else if (threadId == 3)
                                threadIdToRun = 1;

                            object.notifyAll();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

