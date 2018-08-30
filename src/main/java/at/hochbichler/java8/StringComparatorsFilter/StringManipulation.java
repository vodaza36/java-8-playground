package at.hochbichler.java8.StringComparatorsFilter;

public class StringManipulation {
    public static void main(String[] args) {
        // 1. Variant
        final String str = "w00t";
        str.chars().forEach(StringManipulation::printChar);

        // 2. Variant
        str.chars()
                .mapToObj(ch -> Character.valueOf((char)ch))
                .forEach(System.out::println);

    }

    private static void printChar(int c) {
        System.out.println((char)c);
    }


}
