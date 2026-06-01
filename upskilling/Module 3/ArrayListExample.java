import java.util.Scanner;
import java.util.ArrayList;
public class ArrayListExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> studentNames = new ArrayList<>();
        System.out.println("Enter student names (type 'exit' to stop):");
        while (true) {
            System.out.print("Enter name: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            if (!input.isEmpty()) {
                studentNames.add(input);
            }
        }
        System.out.println("\nList of Student Names:");
        for (String name : studentNames) {
            System.out.println("- " + name);
        }
        scanner.close();
    }
}
