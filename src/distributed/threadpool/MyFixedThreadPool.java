package distributed.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 
 * @author liamc
 * Barebones fixedthreadpool implementation
 *
 */
public class MyFixedThreadPool {
	private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
	private boolean isActive = true;
	private Thread[] workerThreads;
	
	MyFixedThreadPool(int threadSize) {
		workerThreads = new Thread[threadSize];
		int i = 0;
		for(Thread worker : workerThreads) {
			i++;
			worker = new Worker("Thread " + i);
			worker.start();
		}
	}
	
	public void submitTask(Runnable r) {
		tasks.add(r);
	}
	
	public void shutDown() {
		isActive = false;
	}
	
	private class Worker extends Thread {
		
		public Worker(String name) {
			super(name);
		}

		public void run() {
			while(isActive) {
				Runnable task;
				try {
					task = tasks.take();
					task.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
