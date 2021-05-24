package distributed.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 
 * @author liamc
 * Barebones fixedthreadpool implementation
 *
 */
public class MySingleThreadPool {
	private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
	private boolean isActive = true;
	private Thread worker;
	
	MySingleThreadPool() {
		worker = new Worker("Worker Thread");
		worker.start();
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
