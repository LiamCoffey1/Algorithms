package distributed.threads;
import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);
    
    public void increment() {
        c.incrementAndGet();
    }
    public void decrement() {
        c.decrementAndGet();
    }
    public int value() {
        return c.get();
    }
    public void initialise(int n) {
    	c.set(n);
    }
    
    public static void main(String args[]) {
        AtomicCounter acounter = new AtomicCounter(); // thread safe counter
        acounter.initialise(5);
        acounter.increment();
        acounter.decrement();
        System.out.println("Atomic Counter "+acounter.value());
        
    }
}

