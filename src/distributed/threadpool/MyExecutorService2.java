package distributed.threadpool;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class MyExecutorService2 implements Callable<Integer> {
    int n;
    
    public MyExecutorService2 (int n) {
    	this.n = n;
    }

    public Integer call() throws Exception {
    	int factorial = 1;
        for (int i=2; i<=n; i++)
        	factorial = factorial*i;
        return factorial;	
    }

    public static void main(String[] args) throws Exception {
    	int NTHREADS=5;
    	ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);
        List<Future<Integer>> list = new ArrayList<>();
    	for (int i=1; i<6; i++)
		   list.add(exec.submit(new MyExecutorService2(i)));

    	int i=1;
		for (Future<Integer> f : list) {
       	   System.out.println("factorial " + i + " = " + f.get());
       	   i++;
		}
    	exec.shutdown();
    }
}
