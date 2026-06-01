import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int count = scanner.nextInt();
        
        if (count <= 0) {
            System.out.println("Error: Number of elements must be greater than zero.");
            scanner.close();
            return;
        }
        
        double[] array = new double[count];
        double sum = 0;
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < count; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextDouble();
            sum += array[i];
        }
        
        double average = sum / count;
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        scanner.close();
    }
}
