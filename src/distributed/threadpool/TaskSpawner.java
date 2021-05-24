package distributed.threadpool;

import java.util.concurrent.BlockingQueue;
/* Thread safe structure achieving effects atomically */
import java.util.concurrent.LinkedBlockingQueue;
/* The LinkedBlockingQueue constructor allows a specific capacity to be set for the FIFO queue */
 
class ThreadPool {
    private final BlockingQueue<Runnable> workerQueue;
    private final Thread[] workerThreads;
 
    public ThreadPool(int numThreads) {
        workerQueue = new LinkedBlockingQueue<Runnable>();
        workerThreads = new Thread[numThreads];
        int i = 0;
        for (Thread t : workerThreads) {
            i++;
            t = new Worker("Pool Thread "+i);
            t.start();
        }
    }
    
    private class Worker extends Thread {
        
        public Worker(String name){
            super(name);
        }
        
        public void run() {
            while (true) {
                try {
                    Runnable r = workerQueue.take();
                    r.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addTask(Runnable r){
        try {
            workerQueue.put(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TaskSpawner {
    public static void main(String args[]) {
	   
	   ThreadPool pool = new ThreadPool(4);
	   for (int i=0; i<10; i++) {
	      pool.addTask(new Runnable() {public void run() {System.out.println("Hello from a Threadpool Task!");} });
	   }   
    }
 }