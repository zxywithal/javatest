package javacore.chapter02.objectStream;

import javacore.chapter02.Employee;
import javacore.chapter02.Manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class ObjectStreamTest {
    public static void main(String[] args) throws Exception {
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl Cracker", 75000, 1987, 12, 15);
        Manager tony = new Manager("Tony tester", 40000, 1990, 3, 15);
        System.out.println(harry.hashCode());
        System.out.println(carl.hashCode());
        System.out.println(tony.hashCode());
        tony.setSecretary(harry);
        carl.setSecretary(harry);
        Employee[] staff = new Employee[3];
        staff[0] = harry;
        staff[1] = carl;
        staff[2] = tony;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))) {
            out.writeObject(staff);
        }
        Employee[] newStaff = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"))){
             newStaff= (Employee[])in.readObject();
        }
        newStaff[0].raiseSalary(100);

        Arrays.stream(newStaff).forEach(obj -> System.out.println(obj));


    }
}
