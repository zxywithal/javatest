package javacore.chapter01;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingIntoMaps {

    public static void main(String[] args) {
        Stream<Person> personStream = Stream.of(new Person(1001, "perter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
        Stream<Person> personStream2 = Stream.of(new Person(1001, "perter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
        Stream<Person> personStream3 = Stream.of(new Person(1001, "perter"), new Person(1002, "Paul"), new Person(1001, "Mary"));
        Map<Integer, String> idToName = personStream.collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(idToName);
        Map<Integer, Person> idToPerson = personStream2.collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println(idToPerson);
        Map<Integer, Person> idToPerson3 = personStream3.collect(Collectors.toMap(Person::getId, Function.identity(), (existValue,newValue)->{ return existValue;}));
        System.out.println(idToPerson3);
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(Locale::getDisplayName, obj->obj.getDisplayName(obj), (existValue, newValue) -> {
            return existValue;
        }));
        System.out.println(languageNames);
        Stream<Locale> locales1 = Stream.of(Locale.getAvailableLocales());
        //转成一个map<String,Set<String>>
        Map<String, Set<String>> collect = locales1.collect(Collectors.toMap(Locale::getDisplayName, obj -> Collections.singleton(obj.getDisplayLanguage()), (existValue, newValue) -> {
            Set<String> union = new HashSet<>(existValue);
            union.addAll(newValue);
            return union;
        }));
        System.out.println(collect);

    }


    public static class Person
    {
        private int id;
        private String name;

        public Person(int id, String name)
        {
            this.id = id;
            this.name = name;
        }

        public int getId()
        {
            return id;
        }

        public String getName()
        {
            return name;
        }

        public String toString()
        {
            return getClass().getName() + "[id=" + id + ",name=" + name + "]";
        }
    }
}
