package javacore.chapter02.textFile;

import javacore.chapter02.Employee;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextFileTest {

    public static void main(String[] args) throws Exception {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony tester", 40000, 1990, 3, 15);
        try (PrintWriter out = new PrintWriter("employee.dat", "utf-8")) {
            writeData(out, staff);
        }

        try (Scanner scanner = new Scanner(new FileInputStream("employee.dat"),"utf-8")) {
            Employee[] newStaff = readData(scanner);
            Stream.of(newStaff).forEach(System.out::println);
        }
    }

    public static Employee[] readData(Scanner in){
        int lines = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[lines];
        for (int i = 0; i < lines; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static Employee readEmployee(Scanner in ){
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        double salary = Double.parseDouble(tokens[1]);
        String name = tokens[0];
        LocalDate localDate = LocalDate.parse(tokens[2]);
        return new Employee(name,salary,localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth());
    }

    public static void writeEmployee(PrintWriter out,Employee e){
        out.println(e.getName()+"|"+e.getSalary()+"|"+e.getHireDay());
    }

    public static void writeData(PrintWriter out, Employee[] employees) {
        out.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(out,employee);
        }
    }
}
