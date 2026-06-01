/**
 * BytecodeInspectionDemo is a simple class to demonstrate Java bytecode inspection.
 *
 * To inspect the bytecode:
 * 1. Compile the file:
 *    javac Q37/BytecodeInspectionDemo.java
 * 2. Run javap tool:
 *    javap -c Q37/BytecodeInspectionDemo
 *
 * Below is the expected output and interpretation:
 * 
 * Compiled from "BytecodeInspectionDemo.java"
 * public class BytecodeInspectionDemo {
 *   public BytecodeInspectionDemo();
 *     Code:
 *        0: aload_0          // Load 'this' reference onto stack
 *        1: invokespecial #1  // Method java/lang/Object."<init>":()V (Superclass constructor)
 *        4: return
 *
 *   public int computeSquare(int);
 *     Code:
 *        0: iload_1          // Load the integer parameter at index 1 (number) onto stack
 *        1: iload_1          // Load it again
 *        2: imul             // Multiply the two top stack integers (number * number)
 *        3: ireturn          // Return the integer result
 * }
 *
 * Explanation of bytecode instructions:
 * - aload_0: Loads the local variable reference at index 0 (which is 'this' inside non-static methods) onto the operand stack.
 * - invokespecial #1: Invokes an instance method; specifically, the superclass constructor (Object's constructor) here to initialize the object.
 * - iload_1: Loads an int value from local variable slot 1 (the first method parameter) onto the operand stack.
 * - imul: Pops two int values from the stack, multiplies them, and pushes the result back onto the stack.
 * - ireturn: Returns an integer value from the method.
 */
public class BytecodeInspectionDemo {
    public int computeSquare(int number) {
        return number * number;
    }
    public static void main(String[] args) {
        BytecodeInspectionDemo demo = new BytecodeInspectionDemo();
        int result = demo.computeSquare(5);
        System.out.println("Bytecode Inspection Demo");
        System.out.println("Square of 5 is: " + result);
    }
}
