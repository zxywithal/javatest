package javacore.chapter01;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStreams {
    public static void main(String[] args) throws Exception {
        IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
        is1.limit(10).forEach(s->{
            System.out.print(" "+s);
        });
        System.out.println();
        IntStream is2 = IntStream.range(5, 10);
        is2.limit(10).forEach(s->{
            System.out.print(" "+s);
        });
        System.out.println();
        IntStream is3 = IntStream.rangeClosed(5, 10);
        is3.limit(10).forEach(s->{
            System.out.print(" "+s);
        });

        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("cities.txt").toURI());
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream intStream = words.mapToInt(String::length);
        intStream.forEach(s->{
            System.out.print(" "+s);
        });
        //这一段骚操作没看名表
        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        String collect = codes.mapToObj(s -> String.format("%X", s)).collect(Collectors.joining());
        System.out.println(collect);

        Stream<Integer> boxed = IntStream.range(0, 100).boxed();
        IntStream intStream1 = boxed.mapToInt(Integer::intValue);

    }
}
