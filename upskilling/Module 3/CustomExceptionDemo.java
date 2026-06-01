import java.util.Scanner;
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
public class CustomExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        try {
            if (age < 18) {
                throw new InvalidAgeException("Age is less than 18. Registration failed.");
            }
            System.out.println("Registration successful. Welcome!");
        } catch (InvalidAgeException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
