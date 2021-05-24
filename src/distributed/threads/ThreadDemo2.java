package distributed.threads;
public class ThreadDemo2 implements Runnable {

   Thread t;
   ThreadDemo2() {    /* Constructor */
      t = new Thread(this, "Admin Thread"); /* This object is the runnable object */
      t.setPriority(1);
      t.start();
   }

   public void run() {
      System.out.println("Name = " + t.getName());
      System.out.print("Id = " + t.getId());
   }

   public static void main(String args[]) {
      new ThreadDemo2();
   }
}

