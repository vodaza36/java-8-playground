package at.hochbichler.java8.functional;

public class ConstructorReference {
    public static void main(String[] args) {
        EmployeeFactory factory = Employee::new;
        Employee emp = factory.createEmoloyee("Thomas", 40);

    }
}
