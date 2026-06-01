import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
class SampleClass {
    private String data;
    public SampleClass() {
        this.data = "Default Reflection Data";
    }
    public void displayMessage() {
        System.out.println("Method displayMessage() executed: " + data);
    }
    public void greet(String name, int times) {
        System.out.println("Method greet() executed: Greeting " + name + " " + times + " times!");
    }
}
public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            String className = "SampleClass";
            Class<?> clazz = Class.forName(className);
            System.out.println("Dynamically loaded class: " + clazz.getName());
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                System.out.println("- " + constructor);
            }
            System.out.println("\nDeclared Methods and Parameters:");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.print("- Method: " + method.getName() + " (Returns: " + method.getReturnType().getSimpleName() + ")");
                Parameter[] params = method.getParameters();
                if (params.length > 0) {
                    System.out.print(" | Parameters: ");
                    for (int i = 0; i < params.length; i++) {
                        System.out.print(params[i].getType().getSimpleName() + " " + params[i].getName());
                        if (i < params.length - 1) System.out.print(", ");
                    }
                }
                System.out.println();
            }
            Object instance = clazz.getDeclaredConstructor().newInstance();
            System.out.println("\nInvoking displayMessage dynamically:");
            Method displayMsgMethod = clazz.getDeclaredMethod("displayMessage");
            displayMsgMethod.invoke(instance);
            System.out.println("\nInvoking greet dynamically:");
            Method greetMethod = clazz.getDeclaredMethod("greet", String.class, int.class);
            greetMethod.invoke(instance, "Alice", 3);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
