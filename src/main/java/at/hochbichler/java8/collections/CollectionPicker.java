package at.hochbichler.java8.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionPicker {
    public final static List<String> NAMES = Arrays.asList("Max", "Sami", "Heiko", "Didi", "Thomas");

    public static void main(String[] args) {
        final Function<String, Predicate<String>> byStartingLetter = letter -> name -> name.startsWith(letter);

        final String foundName = NAMES.stream().filter(byStartingLetter.apply("X")).findFirst().orElse("Name not found!");
        System.out.println("Found: " + foundName );

        // sum all characters
        int sum = NAMES.stream().mapToInt(name -> name.length()).sum();
        System.out.println("SUM: " + sum);

        // find longest name
        String longestName = NAMES.stream().reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2).orElse("not found");
        System.out.println("Longest name is: " + longestName);

        // Join names
        System.out.println("All names: " + String.join(", ", NAMES));

        // to uppercase
        System.out.println("Upper: " + NAMES.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
    }
}
