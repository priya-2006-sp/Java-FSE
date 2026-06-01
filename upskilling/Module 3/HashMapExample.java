import java.util.Scanner;
import java.util.HashMap;
public class HashMapExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> studentMap = new HashMap<>();
        studentMap.put(101, "Alice");
        studentMap.put(102, "Bob");
        studentMap.put(103, "Charlie");
        studentMap.put(104, "Diana");
        System.out.println("Student ID to Name mapping created with IDs: 101, 102, 103, 104");
        System.out.print("Enter student ID to retrieve name: ");
        if (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            String name = studentMap.get(id);
            if (name != null) {
                System.out.println("Student with ID " + id + " is " + name + ".");
            } else {
                System.out.println("No student found with ID " + id + ".");
            }
        } else {
            System.out.println("Error: Invalid ID format. Please enter an integer.");
        }
        scanner.close();
    }
}
