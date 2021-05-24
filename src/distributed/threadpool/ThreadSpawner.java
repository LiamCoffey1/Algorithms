package distributed.threadpool;

class Activity implements Runnable {
    public void run() {
        System.out.println("Hello from a runnable object Activity!");
    }
}

public class ThreadSpawner {
    public static void main(String args[]) {
 
       Thread t1 = new Thread(new Activity());
	   t1.start();

	 /* Creating a runnable object from an inline class definition */
       Thread t2 = new Thread(new Runnable() {
           public void run() {
               System.out.println("Hello from a runnable inline object!");
            }
        });
	   t2.start();
    }
 }
