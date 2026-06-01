class MessageRunnable implements Runnable {
    private String message;
    private int count;
    public MessageRunnable(String message, int count) {
        this.message = message;
        this.count = count;
    }
    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + message + " (Iteration " + i + ")");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        }
    }
}
public class ThreadCreationDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MessageRunnable("Hello from Thread A", 5), "Thread-A");
        Thread thread2 = new Thread(new MessageRunnable("Greetings from Thread B", 5), "Thread-B");
        System.out.println("Starting threads...");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("All threads finished.");
    }
}
