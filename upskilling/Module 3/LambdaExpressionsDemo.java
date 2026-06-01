import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class LambdaExpressionsDemo {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Orange");
        fruits.add("Cherry");
        System.out.println("Original list: " + fruits);
        Collections.sort(fruits, (s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted list (alphabetical): " + fruits);
    }
}
