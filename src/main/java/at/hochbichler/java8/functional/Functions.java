package at.hochbichler.java8.functional;

import com.google.common.util.concurrent.Uninterruptibles;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Functions {
    public static void main(String[] args) {
        // example of a functional interface
        // public interface Function<T, R> { … }
        Map<String, Integer> nameMap = new HashMap<>();
        nameMap.put("Thomas", 6);
        nameMap.put("Julia", 5);
        System.out.println("Thomas: " + nameMap.computeIfAbsent("Thomas", s -> {
            System.out.println("Computer value");
            return s.length();
        }));
        System.out.println("Julia: " + nameMap.computeIfAbsent("Julia", s -> s.length()));
        System.out.println("Marion: " + nameMap.computeIfAbsent("Marion", s -> {
            System.out.println("Computer value");
            return s.length();
        }));

        // Functional interfaces can be composed
        Function<Integer, String> intToString = Objects::toString;
        Function<String, String> quote = s-> "'" + s + "'";

        Function<Integer,String> quoteIntToString = quote.compose(intToString);
        System.out.println("Quoted Int: " + quoteIntToString.apply(100));

        // What are primitive Functions
        IntFunction<String> intToStringFunc = Objects::toString;
        System.out.println("IntToStringFunc: " + intToStringFunc.apply(4711));

        // Here a list of all Functional interfaces
        // see: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

        // Two-Arity Function Specializations
        // e.g. map.replaceAll
        Map<String, Integer> saleries = new HashMap<>();
        saleries.put("John", 40000);
        saleries.put("Freddy", 30000);
        saleries.put("Samuel", 50000);

        saleries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);
        saleries.forEach((name, salery) -> System.out.println(name + ": " + salery));

        // Supplier
        Supplier<Double> lazyValue = () -> {
            Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
            return 9d;
        };

        double v = squareLazy(lazyValue);
        System.out.println("Square lazy: " + v);

        // Consumer
        saleries.forEach((name, salery) -> System.out.println("Salery for " + name + " is " + salery));

        // Predicates
        List<String> bestSaleries = saleries.entrySet().stream().filter(entry -> entry.getValue() > 30000).map(entry -> entry.getKey()).collect(Collectors.toList());
        bestSaleries.forEach(System.out::println);

        // Operators
        List<String> names = Arrays.asList("thomas", "julia", "marion");
        names.replaceAll(String::toUpperCase);
        names.forEach(System.out::println);

    }

    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }
}
