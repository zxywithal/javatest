package javacore.chapter01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("cities.txt").toURI());
        String contents = new String(Files.readAllBytes(path));
        String[] split = contents.split("\\PL+");

        Stream<String> stream = Stream.of(split);
        Stream<String> stringStream = stream.map(s -> s.replaceAll("[aeiouAEIOU]", ""));

        Iterator<Integer> iterator = Stream.iterate(0, s -> s + 1).limit(10).iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        Integer[] integers = Stream.iterate(1, s -> s + 1).limit(10).toArray(Integer[]::new);
//        Integer[] integers = (Integer[]) objects;
        System.out.println(integers);

        System.out.println("number 0 "+integers[0]);
        System.out.println("number 0 "+integers[2]);

        stringStream.forEach(System.out::println);

//        System.out.println(stringStream.collect(Collectors.toSet()).stream().limit(10).map(Object::toString).collect(Collectors.joining(",")));
//        System.out.println(stringStream.limit(10).map(Object::toString).collect(Collectors.joining()));
//        IntSummaryStatistics summary = stringStream.collect(Collectors.summarizingInt(String::length));
//        System.out.println(summary.getMax());
//        System.out.println(summary.getMin());
//        System.out.println(summary.getAverage());
//        System.out.println(summary.getCount());
//        System.out.println(summary.getSum());


    }
}
