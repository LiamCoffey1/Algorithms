package distributed.threads;
public class SleepMessages2 {
    public static void main(String args[]) {
        String message[] = {
            "Waited 4 Seconds",
            "Waited 8 seconds",
            "Waited 12 seconds",
            "Waiting 16 seconds"
        };

        for (int i = 0; i < message.length; i++) {
            //Pause for 4 seconds
            try {
                // this method can throw an InterruptedException
                Thread.sleep(4000); 
                } catch (InterruptedException e) {
                      //We've been interrupted: no more messages.
                      return;
                      }
            System.out.println(message[i]);
        }
    }
}
