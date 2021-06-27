package javacore.chapter02.randomAccess;

import javacore.chapter02.Employee;

import java.io.*;
import java.util.stream.Stream;

public class RandomAccessTest {

    public static void main(String[] args) throws Exception {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony tester", 40000, 1990, 3, 15);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            writeData(out, staff);
        }

        try (RandomAccessFile in = new RandomAccessFile("employee.dat","r")) {
            Employee[] newStaff = readData(in);
            Stream.of(newStaff).forEach(System.out::println);
        }
    }

    public static Employee[] readData(RandomAccessFile in) throws IOException {
        long length = in.length();
        int n = (int)length/Employee.RECORD_SIZE;
        Employee[] employees = new Employee[n];

        for (int i = n - 1; i>=0; i --) {
            in.seek(i*Employee.RECORD_SIZE);
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static Employee readEmployee(DataInput in ) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        return new Employee(name, salary, in.readInt(), in.readInt(), in.readInt());
    }

    public static void writeEmployee(DataOutput out,Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(),Employee.NAME_SIZE,out);
        out.writeDouble(e.getSalary());
        out.writeInt(e.getHireDay().getYear());
        out.writeInt(e.getHireDay().getMonthValue());
        out.writeInt(e.getHireDay().getDayOfMonth());
    }

    public static void writeData(DataOutput out, Employee[] employees) throws IOException {
        for (Employee employee : employees) {
            writeEmployee(out,employee);
        }
    }
}
