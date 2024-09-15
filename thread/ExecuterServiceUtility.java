package src.com.pack.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

class Service implements Runnable {
    int i;
    public Service(int i) {
        this.i = i;
    }
    @Override
    public void run() {
        System.out.println("In thread: "+ i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Service2 implements Callable<String>{

    int i;
    public Service2(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        System.out.println("In thread: "+ i);
        return "from thread: "+ i;
    }
}


public class ExecuterServiceUtility {
    public static void main (String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);
        System.out.println(new Date());

        // Using Submit
        List<Future<String>> list = new ArrayList<>();
        for (int i =0; i< 25; i++){


            // Using callable
            list.add((Future<String>) es.submit(new Service2(i)));

            // Using submit - Runnable
           // es.execute(new Service(i));

            list.add((Future<String>) es.submit(new Service(i)));

            es.shutdown();
            es.awaitTermination(10, TimeUnit.SECONDS);

            System.out.println("==================");
            for(Future<String> fut : list)
                System.out.println(fut);

            System.out.println(new Date());

        }

    }
}
