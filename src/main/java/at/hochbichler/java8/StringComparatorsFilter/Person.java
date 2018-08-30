package at.hochbichler.java8.StringComparatorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(Person other) {
        return age - other.getAge();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );

        List<Person> ascendingAge = people.stream()
                .sorted(Person::ageDifference)
                .collect(Collectors.toList());
        ascendingAge.forEach(System.out::println);

        Comparator<Person> compareAscending = Person::ageDifference;
        Comparator<Person> compareDecending = compareAscending.reversed();

        List<Person> ascendingAge2 = people.stream()
                .sorted(compareDecending)
                .collect(Collectors.toList());
        ascendingAge2.forEach(System.out::println);

        // Who is the youngest?
        Optional<Person> youngest = people.stream().min(Person::ageDifference);
        System.out.println("Youngest is: " + youngest.orElseGet(null));

        // Sort by name and age
        final Function<Person, String> byName = person -> person.getName();
        final Function<Person, Integer> byAge = person -> person.getAge();
        people.stream()
                .sorted(comparing(byName).thenComparing(byAge))
                .forEach(System.out::println);

        // Use a collector to collect peoples older than
        List<Person> oldPeople = people.stream()
                .filter(p -> p.getAge() > 30)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        oldPeople.forEach(System.out::println);

        // Use a groupBy Collector
        Map<Integer, List<Person>> peopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("People group by age: " + peopleByAge);
    }
}
