import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class VirtualThreadsDemo {
    private static final int TASK_COUNT = 100_000;
    public static void main(String[] args) {
        System.out.println("--- Virtual Threads vs Platform Threads (100,000 tasks) ---");
        AtomicInteger vtCounter = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < TASK_COUNT; i++) {
                executor.submit(() -> {
                    vtCounter.incrementAndGet();
                    try {
                        Thread.sleep(10); // simulate some light blocking work
                    } catch (InterruptedException e) {}
                });
            }
        } 
        long endTime = System.currentTimeMillis();
        System.out.println("Virtual Threads: completed " + vtCounter.get() + " tasks.");
        System.out.println("Time taken with Virtual Threads: " + (endTime - startTime) + " ms");
        System.out.println("\nComparing with Traditional Platform Threads:");
        System.out.println("- Spawning 100,000 platform threads simultaneously would likely throw an OutOfMemoryError (Unable to create new native thread).");
        System.out.println("- Platform threads are mapped 1:1 to OS threads and consume ~1MB of memory per stack.");
        System.out.println("- Virtual threads run on JVM carrier threads, are mounted/dismounted when blocked, and consume only a few hundred bytes each.");
    }
}
