package distributed.threadpool;


import java.util.concurrent.*;

public class MyExecutorService {
    private static final int NTHREADS = 10;
    private static final ExecutorService exec
        = Executors.newFixedThreadPool(NTHREADS);
 
    public static void main(String[] args) throws Exception {
    	
	Future<String> future = exec.submit(
		new Callable<String>(){
    		    public String call() throws Exception {
    	              System.out.println("Asynchronous Callable");
    	              return "Callable Result";
    	           }
    	       });
 
    	System.out.println("future.get() = " + future.get());
    	exec.shutdown();
	exec.awaitTermination(5, TimeUnit.SECONDS);
    	if (exec.isTerminated()) 
    		System.out.println("Thread pool shutdown");
    	else 
    	   System.out.println("Failed to shutdown pool in 5 seconds");
    }
}

