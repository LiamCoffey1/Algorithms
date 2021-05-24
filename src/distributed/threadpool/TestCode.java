package distributed.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCode {

	public static void main(String args[]) {
		MyFixedThreadPool pool = new MyFixedThreadPool(5);
		for(int i = 0; i < 5; i++)
			pool.submitTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("test ");
				}
			});
		pool.shutDown();
		
		MySingleThreadPool singlePool = new MySingleThreadPool();
			singlePool.submitTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("Single Pool ");
				}
			});
			singlePool.submitTask(new Runnable() {
				@Override
				public void run() {
					System.out.println("Single Pool ");
				}
			});
		singlePool.shutDown();
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 5; i++)
		executorService.execute(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		    }
		});

		executorService.shutdown();
	}
}
