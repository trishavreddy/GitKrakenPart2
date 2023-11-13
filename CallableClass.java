import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.List;
import java.util.ArrayList;

public class CallableClass implements Callable<Integer>
{

    public static int sum = 0;

    @Override
    public Integer call() throws Exception {

        
        int count = 0;
        for(int i = 0; i < 1000000; i++)
        {
            
            count++;
        }
        return count;
    }

    public static void main(String args[]){

        ExecutorService executor = Executors.newFixedThreadPool(10);

        //create a list to hold the Future object associated with Callable
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        
        Callable<Integer> callable = new CallableClass();
        for(int i=0; i< 100; i++){
            Future<Integer> future = executor.submit(callable);
            list.add(future);
        }
        for(Future<Integer> fut : list){
            try {
                sum += fut.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //shut down the executor service now
        executor.shutdown();
        System.out.println("Sum: "+sum);
    }
    


}