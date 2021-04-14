package java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

 class Thread1 implements Runnable {

	@Override
	public void run() {
		
		while(true)
			System.out.println("thread 1");
	}
	
}

 class Thread2 implements Runnable {

	@Override
	public void run() {
		while(true)
			System.out.println("thread 2");
		
	}
	
}

 class Threads {

	
	public static void main(String args[]) {
		ExecutorService executor = Executors.newFixedThreadPool( 10 );
		Runnable thread = new Thread1();
		Runnable thread2 = new Thread2();
		executor.submit(thread);
		executor.submit(thread2);
	}
}
