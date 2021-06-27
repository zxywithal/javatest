package javacore.chapter01;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\javatest\\java8test\\src\\aaa.txt");
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        List<String> strings = Arrays.asList(content.split("\\PL+"));
//        System.out.println(strings.stream().filter(obj->obj.length() > 12).count());
//        System.out.println(strings);
//        Stream<String> uppercaseMap = strings.stream().map(String::toUpperCase);
//        uppercaseMap.forEach(obj-> System.out.println(obj));
        Stream<String> lowercase = strings.stream().map(obj -> obj.toLowerCase());
//        lowercase.forEach(obj-> System.out.println(obj));
//        Stream<Stream<String>> streamStream = strings.stream().map(StreamTest::letters);
//        streamStream.forEach(obj->{
//            obj.forEach(iner-> System.out.print(iner));
//        });
//        Stream<String> stringStream = strings.stream().flatMap(StreamTest::letters);
//        stringStream.forEach(obj-> System.out.print(obj));


//        Stream<String> stringStream = Stream.of("123", "gently", "down", "the", "stream");
//        stringStream.forEach(obj-> System.out.println(obj));
//        Stream<Object> empty = Stream.empty();
//        empty.forEach(obj-> System.out.println(obj));

        /**
         * 三种产生无限流的方式
         */
//        Stream<Integer> generate = Stream.generate(() -> 2+1);
//        generate.forEach(obj-> System.out.println(obj));
//        Stream<Double> generate1 = Stream.generate(Math::random);
//        generate1.forEach(obj-> System.out.println(obj));
//        Stream<Integer> iterate = Stream.iterate(1, n -> n + 3);
//        iterate.forEach(obj-> System.out.println(obj));
//        Stream<String> stringStream1 = Pattern.compile("\\PL+").splitAsStream(content);
//        stringStream1.forEach(obj-> System.out.println(obj));
//
//        Stream<String> lines = Files.lines(path);
//        lines.forEach(obj-> System.out.println(obj));
//        System.out.println(StandardCharsets.UTF_8.name());

//        Stream<Double> limit = Stream.generate(Math::random).limit(2);
//        Stream<String> concat = Stream.concat(StreamTest.letters("Hello"), StreamTest.letters("World"));
//        System.out.println("======================");
//        limit.forEachOrdered(obj-> System.out.println(obj));


        //distinct
//        Stream<String> distinct = Stream.of("123", "321", "merrily", "merrily").distinct();
//        distinct.forEach(obj-> System.out.println(obj));
        //sorted
//        strings.stream().sorted().forEach(obj-> System.out.println(obj));
//        strings.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(obj-> System.out.println(obj));
        //peek
        Stream<Integer> limit = Stream.iterate(1, p -> p + 1).peek(obj-> System.out.println(obj)).limit(20);


    }

    public static Stream<String> letters(String s) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            list.add(s.substring(i, i + 1));
        }
        return list.stream();
    }
}
