package at.hochbichler.java8;

import at.hochbichler.java8.functional.CustomFunctionalInterface;
import at.hochbichler.java8.functional.CustomInterfacePrinter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Welchome to my Java 8 plaground!");
        CustomFunctionalInterface ci = (String p) -> System.out.println("Hello: " + p);
        ci.firstMethod("Thomas");

        CustomInterfacePrinter ip = new CustomInterfacePrinter();
        ip.print(ci);
        ip.print(p-> System.out.println("Shit"));
    }
}
