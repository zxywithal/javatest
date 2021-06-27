package javacore.chapter02.serialClone;

import javacore.chapter02.Employee;
import javacore.chapter02.randomAccess.DataIO;

public class SerialConeTest {
    public static void main(String[] args) throws Exception {
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Employee clone = (Employee)DataIO.clone(harry);
        System.out.println(harry.hashCode());
        System.out.println(clone.hashCode());

    }
}
