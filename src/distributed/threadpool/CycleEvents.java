package distributed.threadpool;

abstract class CycleEvent {
	private boolean isRunning = true;
	public boolean isRunning() {
		return isRunning;
	}
	public void stop() {
		isRunning = false;
	}
	public abstract void run();
	public abstract void onComplete();
}

class CycleEventExcecutor {

	private static CycleEventExcecutor singleton;
	public static CycleEventExcecutor getSingleton() {
		if(singleton == null)
			return new CycleEventExcecutor();
		else return singleton;
	}
	public void submitTask(CycleEvent event, int cycles, long timeToSleep, long offset) {
		new Worker(event, cycles, timeToSleep, offset).start();
	}
}

class Worker extends Thread {

	int cycles;
	long timeToSleep, offset;
	CycleEvent event;

	public Worker(CycleEvent event, int cycles, long timeToSleep, long offset) {
		super("Cycle event worker");
		this.cycles = cycles;
		this.timeToSleep = timeToSleep;
		this.event = event;
	}

	public void run() {
		try {
			Thread.sleep(offset);
			int cycle = 0;
			while (cycle != cycles && event.isRunning()) {
				event.run();
				cycle++;
				Thread.sleep(timeToSleep);
			}
			event.onComplete();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

public class CycleEvents {

	public static void main(String args[]) {
		CycleEventExcecutor executor = CycleEventExcecutor.getSingleton();
		CycleEvent event1 = new CycleEvent() {
			@Override
			public void run() {
				System.out.println("Hello World!");
			}

			@Override
			public void onComplete() {
				System.out.println("Event 1 complete");
			}
		};
		CycleEvent event2 = new CycleEvent() {
			@Override
			public void run() {
				System.out.println("Hello World 2!");
			}

			@Override
			public void onComplete() {
				System.out.println("Event 2 complete");
			}
		};
		executor.submitTask(event1, 5, 3000, 0);
		executor.submitTask(event2, 5, 2000, 0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Stop event 2, should have ran for 1 cycle
		event2.stop();
	}

}
