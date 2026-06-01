public class PatternMatchingSwitch {
    public static void identifyType(Object obj) {
        String typeInfo = switch (obj) {
            case Integer i -> "Integer: " + i + " (Square: " + (i * i) + ")";
            case String s  -> "String: \"" + s + "\" (Length: " + s.length() + ")";
            case Double d  -> "Double: " + d + " (Half: " + (d / 2.0) + ")";
            case Boolean b -> "Boolean: " + b;
            case null      -> "Null object";
            default        -> "Unknown type: " + obj.toString();
        };
        System.out.println(typeInfo);
    }
    public static void main(String[] args) {
        System.out.println("Testing pattern matching switch in Java 21:");
        identifyType(42);
        identifyType("Hello, Java 21!");
        identifyType(3.14159);
        identifyType(true);
        identifyType(null);
        identifyType(new Object());
    }
}
