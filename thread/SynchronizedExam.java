package src.com.pack.thread;

import javax.swing.*;

public class SynchronizedExam {

    public static void main(String[] args) {
        Display d1 = new Display();
        Display d2 = new Display();
        MyThread t1 = new MyThread(d1, "Dhoni");
        MyThread t2 = new MyThread(d2, "Dhoni");
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}

class MyThread extends Thread {
    Display d;
    String name;
    public MyThread(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    @Override
    public void run() {
        d.wish(name);
    }
}

class Display {
    public static synchronized void wish(String msg) {
        for (int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName()+" : "+ msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
