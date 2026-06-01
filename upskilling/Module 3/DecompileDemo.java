/**
 * DecompileDemo is a class written to demonstrate compilation and reverse engineering.
 * 
 * Compilation:
 *   javac Q38/DecompileDemo.java
 * 
 * When decompiled using CFR (java -jar cfr.jar Q38/DecompileDemo.class) or JD-GUI:
 * 
 * 1. String Concatenation:
 *    Original: String message = "Hello " + name;
 *    Decompiled: String message = "Hello " + name; 
 *    (Note: JDK 9+ compiles string concatenation into invokedynamic calling StringConcatFactory, 
 *     which the decompiler successfully reconstructs back to the simple '+' syntax.)
 * 
 * 2. Enhanced For-Loop (foreach):
 *    Original: for (String fruit : fruits) { ... }
 *    Decompiled: 
 *      String[] arrstring = fruits;
 *      int n = arrstring.length;
 *      for (int i = 0; i < n; ++i) {
 *          String fruit = arrstring[i];
 *          ...
 *      }
 *    (For arrays, compiler turns foreach into standard indexed for-loop. For Collections, it uses Iterator.)
 */
public class DecompileDemo {
    public static void main(String[] args) {
        String name = "Decompiler";
        String message = "Hello " + name;
        System.out.println(message);
        String[] fruits = {"Apple", "Banana", "Cherry"};
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }
    }
}
