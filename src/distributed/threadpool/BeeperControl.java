package distributed.threadpool;

import java.util.concurrent.*;

class BeeperControl {
	private final ScheduledExecutorService scheduler = 
			Executors.newScheduledThreadPool(1);

	public void timedBeep(int cancelTaskStartTime, int initialDelay, 
			int intervalPeriod, long start) throws Exception {
		
		/* Create the task to be executed periodically */
		final Runnable beeper = new Runnable() {
									public void run() { System.out.println("beep"); }
					    };
		/* Start the periodic beeper task on the executor */
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate
                              (beeper, initialDelay, intervalPeriod, TimeUnit.SECONDS);

		/* Start the delayed execution cancellation task */
		scheduler.schedule(new Runnable() { public void run() { beeperHandle.cancel(true); }}, 
				cancelTaskStartTime, TimeUnit.SECONDS);
		
		/* Put the main thread to sleep for a while to wait for the scheduled tasks */
		Thread.sleep(25000);
		scheduler.shutdown();
		System.out.println("Thread pool shutdown");
		System.out.println(System.currentTimeMillis() - start);
	}
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		BeeperControl beeper = new BeeperControl();
		beeper.timedBeep(20, 2, 8, start); /* parameters - cancel task start time, initial delay, interval period */
    }
}



