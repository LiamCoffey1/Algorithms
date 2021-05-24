package distributed.threads;
public class SleepMessages {
    public static void main(String args[]) 
                      throws InterruptedException {
        String message[] = {
            "Waited 4 Seconds",
            "Waited 8 seconds",
            "Waited 12 seconds",
            "Waiting 16 seconds"
        };
 
        for (int i = 0; i < message.length; i++) {
            //Pause for 4 seconds
            Thread.sleep(4000); /* approximate */
            //Print a message
            System.out.println(message[i]);
        }
    }
}
