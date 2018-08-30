package at.hochbichler.java8.collections;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionProcessing {
    private List<String> friends = Arrays.asList("Max", "Sepp", "Fritz", "Karl");

    public static Predicate<String> filterPredicate(final String letter) {
        return s -> s.startsWith(letter);
    }


    public List<String> getFriends() {
        return friends;
    }

    public static void main(String[] args) {
        CollectionProcessing c = new CollectionProcessing();
        c.getFriends().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("A Friend: " + s);
            }
        });

        c.getFriends().forEach(System.out::println);
        c.getFriends().forEach(c::printName);
        c.getFriends().stream().map(String::toUpperCase).forEach(System.out::println);
        c.getFriends().stream().filter(n->n.startsWith("K")).forEach(System.out::println);


        Consumer<String> consolePrinter = System.out::println;
        c.getFriends().stream().filter(filterPredicate("F")).forEach(consolePrinter);

        final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

        c.getFriends().stream().filter(startsWithLetter.apply("S")).forEach(consolePrinter);
    }

    public void printName(String name) {
        System.out.println("Name: " + name);
    }
}
