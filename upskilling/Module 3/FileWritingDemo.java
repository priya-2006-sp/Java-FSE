import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class FileWritingDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to write to file: ");
        String text = scanner.nextLine();
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text);
            System.out.println("Data successfully written to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
