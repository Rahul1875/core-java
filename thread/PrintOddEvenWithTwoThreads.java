package src.com.pack.thread;


public class PrintOddEvenWithTwoThreads {

    static int N =10;
    volatile static int counter = 1;
    public static void main(String[] args) throws InterruptedException {

        PrintOddEvenWithTwoThreads obj = new PrintOddEvenWithTwoThreads();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.printOddNumber();
            }
        });
        t1.setName("Odd");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.printEvenNumber();
            }
        });
        t2.setName("Even");

        t1.start();
        t2.start();
    }

    private synchronized void printOddNumber() {
        while (counter<=N) {
            if (counter%2==1) {
                System.out.println(Thread.currentThread().getName()+": "+counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private synchronized void printEvenNumber() {
        while (counter<=N) {
            if (counter%2==0) {
                System.out.println(Thread.currentThread().getName()+": "+counter);
                counter++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


