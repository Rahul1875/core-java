package src.com.pack.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ExecuterServiceUtility2 {

    public static void main (String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(2);
        System.out.println(new Date());

        // Using invokeAny()
        List<Callable<String>> list = new ArrayList<>();
        list.add(new Service2(1));
        list.add(new Service2(2));
        list.add(new Service2(3));
        list.add(new Service2(4));
        list.add(new Service2(5));

        //String str = es.invokeAny(list);

        // Using invokeAll
        List<Future<String>> futList = es.invokeAll(list);

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("==================");

       // System.out.println(str);
        for(Future<String> fut : futList)
            System.out.println(fut.get());

        System.out.println(new Date());

    }
}
