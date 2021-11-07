package com.example.nelsdemo;

import java.util.*;

import static java.util.stream.Collectors.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaDemo {
    /* Streams API Features
     * Lazy
     * Avoid shared mutability
     * Collectors.collect is reduce function
     * streams have 2 kind of fn intermediate(returns Streams) and terminal (return value)
     * Functional Interfaces can be chained Ex. func1.andThen(func2)
     * Functional Interfaces can have static and default methods
     * Collectors.partitioningBy -> divide collection into 2 based on predicate
     * */
    public static void main(String[] args) {
        /*list();
        map();
        streams();
        reduce();//terminal/eager function
        convertCollectionToMap(Stream.of(
                        new Employee(1, "Ajay", 20000),
                        new Employee(3, "Yuvi", 10000),
                        new Employee(2, "Amar", 10000))
                .collect(toList()));*/
        grouping(); //Collectors.groupingBy()
        //partitioningBySal();//divide collection into 2 based on predicate
        //parallelStreamDemo();
    }

    private static void partitioningBySal() {
        Map<Boolean, List<Employee>> map = getEmployees().stream()
                .collect(partitioningBy(e -> e.getSalary() > 10000));
        print(map);
    }
    private static void parallelStreamDemo() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        print(integers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .sum());
        print(integers.parallelStream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .sum());
        print(integers.stream()
                .parallel()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .sum());
    }
    private static List<Employee> getEmployees() {
        return Stream.of(
                        new Employee(1, "Ajay", 20000),
                        new Employee(3, "Yuvi", 10000),
                        new Employee(2, "Ajay", 10000))
                .collect(toList());
    }

    private static void grouping() {
        // list all employee with same name and return map
        // i.e. map<name,list<Emp>>
        List<Employee> employees = getEmployees();

        Map<String, List<Employee>> collect =
                employees.stream()
                        .collect(groupingBy(Employee::getName, toList()));//toList is implicit here
        print(collect);
        // grouping and mapping
        // i.e map of name -> list<Salary>

        Map<String, List<Double>> nameSalariesMap =
                employees.stream()
                        .collect(groupingBy(Employee::getName, //name
                                mapping(Employee::getSalary, toList()))); // salary as list
        print(nameSalariesMap);

        Map<String, Long> nameSalariesCount =
                employees.stream()
                        .collect(groupingBy(Employee::getName, //name
                                mapping(Employee::getSalary, counting())));//
        print(nameSalariesCount);
    }

    private static void convertCollectionToMap(List<Employee> employees) {
        // create Map key=name-id, value=Emp object
        //toMap (key,value)
        print(employees.stream()
                .collect(
                        toMap(
                                e -> e.getName() + "-" + e.getId(),// key
                                e -> e))); // value
    }

    private static void reduce() {
        print(Stream.of(
                        new Employee(1, "Ajay", 20000),
                        new Employee(3, "Yuvi", 10000),
                        new Employee(2, "Amar", 10000))
                .mapToDouble(Employee::getSalary)
                .reduce(0, (s1, s2) -> s1 + s2));
        print(Stream.of(
                        new Employee(1, "Kumar", 20000),
                        new Employee(3, "Yuvi", 10000),
                        new Employee(2, "Amar", 10000))
                .map(Employee::getSalary)
                .reduce(0.0, (s1, s2) -> s1 + s2));
        print(Stream.of(
                        new Employee(1, "Kumar", 20000),
                        new Employee(3, "Yuvi", 10000),
                        new Employee(2, "Amar", 10000))
                .reduce((s1, s2) -> new Employee(0, "Sum Sal", s1.getSalary() + s2.getSalary())));
    }

    private static void streams() {
        // feature - lazy
        IntStream.range(0, 10).forEach(LambdaDemo::print);
        Stream.of(
                        new Employee(1, "Abhishek Kumar", 10000),
                        new Employee(3, "Yuvraj", 10000),
                        new Employee(2, "Prakash", 10000))
                .collect(toList())
                .forEach(LambdaDemo::print);
        print(Stream.iterate(100, e -> e + 1));//infinite streams, can't exist without lazyness
        // lazy function returns streams
        //eager function returns some value
    }

    private static void list() {
        List<Employee> employees = Stream.of(
                        new Employee(1, "Abhishek Kumar", 10000),
                        new Employee(3, "Yuvraj", 20000),
                        new Employee(2, "Prakash", 30000))
                .collect(toList());
        //sort employee on salary DESC
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary)
                        .reversed())
                .collect(toList())
                .forEach(LambdaDemo::print);
        print("=====================");
        //sort employee on salary, Name, Id
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))//elegant way
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))// old way
                .collect(toList())
                .forEach(LambdaDemo::print);
        // create comma separated names in upper case having salary > 100000
        print("create comma separated names in upper case having salary > 100000");
        print(employees.stream()
                .filter(e -> e.getSalary() > 10000)
                .map(e -> e.getName().toUpperCase())
                .collect(joining(",")));
    }

    private static void map() {
        Map<Employee, String> empAddressMap = new HashMap<>();
        empAddressMap.put(new Employee(1, "Abhishek", 10000), "Jh");
        empAddressMap.put(new Employee(2, "Suraj", 20000), "Jh");
        empAddressMap.put(new Employee(3, "Ram", 30000), "Bihar");

        print(empAddressMap);
        // ex.1 sort on id DESC
        empAddressMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getId)
                        .reversed()))
                .forEach(LambdaDemo::print);
        print("--------------------");
        // ex.2 sort on id DESC
        empAddressMap.entrySet()
                .stream()
                .sorted((ePrev, eNext) -> eNext.getKey().getId() - ePrev.getKey().getId())
                .forEach(LambdaDemo::print);
        print("------------------------");
        // map(1 to 1) vs flat map (1 to many, stream of stream to stream)
        List<Employee> employees = Stream
                .of(new Employee(1, "Abhishek", 10000, Arrays.asList("12345", "67891")),
                        new Employee(2, "Suraj", 20000, Arrays.asList("67678", "12312")))
                .collect(toList());
        // map -> transform, get names from emp list, one to one
        print(employees.stream()
                .map(Employee::getName)
                .collect(toList()));
        print("------------------------");
        // flat map -> transform + flattering, list of phones to phones
        print(employees.stream()
                .flatMap(e -> e.getMobiles().stream())
                .collect(toList()));
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }
}
