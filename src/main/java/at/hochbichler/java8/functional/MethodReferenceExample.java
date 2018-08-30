package at.hochbichler.java8.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodReferenceExample {
    public static void main(String[] args) {
        Function<String, Double> doubleConverter = Double::parseDouble;
        Function<String, Double> doubleConverterLambda = (String s) -> Double.parseDouble(s);

        System.out.println("1.: " + doubleConverter.apply("100"));
        System.out.println("2.: " + doubleConverterLambda.apply("200"));

        Consumer<String> stringPrinter = System.out::println;
        stringPrinter.accept("Hello World from StringPrinter consumer!");

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        BiPredicate<List<Integer>, Integer> isPartOf=List::contains;
        BiPredicate<List<Integer>, Integer> isPartOfLambda=(List<Integer> list, Integer value) -> list.contains(value);
        System.out.println("Is 1 a part " + isPartOf.test(integerList, 1));
        System.out.println("Is 1 a part " + isPartOfLambda.test(integerList, 1));
    }
}
