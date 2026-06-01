import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
class FactorialTask implements Callable<Long> {
    private int number;
    public FactorialTask(int number) {
        this.number = number;
    }
    @Override
    public Long call() throws Exception {
        // Simulate computation time
        Thread.sleep(200);   
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
public class ExecutorServiceCallable {
    public static void main(String[] args) {
        int threadPoolSize = 3;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        System.out.println("ExecutorService started with a fixed thread pool of " + threadPoolSize + " threads.");
        List<Future<Long>> futures = new ArrayList<>();
        int[] numbersToCalculate = {5, 6, 7, 8, 9, 10};
        for (int num : numbersToCalculate) {
            System.out.println("Submitting factorial task for number: " + num);
            Future<Long> future = executor.submit(new FactorialTask(num));
            futures.add(future);
        }
        System.out.println("\nCollecting results (main thread blocking on Future.get())...");
        for (int i = 0; i < numbersToCalculate.length; i++) {
            int num = numbersToCalculate[i];
            Future<Long> future = futures.get(i);
            try {
                long result = future.get(); // Blocks until task completes
                System.out.println("Factorial of " + num + " is: " + result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error calculating factorial for " + num + ": " + e.getMessage());
            }
        }
        System.out.println("\nShutting down ExecutorService...");
        executor.shutdown();
    }
}
