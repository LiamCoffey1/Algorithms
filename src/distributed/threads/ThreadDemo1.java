package distributed.threads;
public class ThreadDemo1 implements Runnable {

   public void run() {
      Thread t = Thread.currentThread();
      System.out.print(t.getName());
      System.out.println(", alive status = " + t.isAlive());
   }

   public static void main(String args[]) throws Exception {
      Thread t = new Thread(new ThreadDemo1());
      t.start();
      t.join(2000);
      System.out.println("Main thread after waiting for 2000 milliseconds ");
      System.out.print(t.getName());
      System.out.println(", alive status = " + t.isAlive());
   }
} 
