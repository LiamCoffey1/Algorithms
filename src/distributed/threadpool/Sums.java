package distributed.threadpool;

import java.util.*;
import java.util.concurrent.*;
import static java.util.Arrays.asList;

public class Sums {
    /* This callable object will calculate the sum of numbers in a given range */
    static class Sum implements Callable<Long> {
        private final long from;
        private final long to;
   
        Sum(long from, long to) { // Constructor
            this.from = from;
            this.to = to;
        }
        

        public Long call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            return acc;
        }                
    }
    
    public static void main(String[] args) throws Exception {
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List <Future<Long>> results = executor.invokeAll(asList(
            new Sum(0, 10), new Sum(100, 1_000), new Sum(10_000, 1_000_000)
        ));
        executor.shutdown();
        
        for (Future<Long> result : results) {
            System.out.println(result.get());
        }                
    }    
}

