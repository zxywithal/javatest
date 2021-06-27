package javacore.chapter01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreams {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("cities.txt").toURI());
        List<String> wordList = Arrays.asList(new String(Files.readAllBytes(path)).split("\\PL+"));
        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s->{
            if(s.length()<10)
                shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s->{
            if(s.length()<10)
                shortWords[s.length()]++;
        });
        System.out.println(Arrays.toString(shortWords));

        Map<Integer, Long> shortWordCounts = wordList.parallelStream().filter(s -> s.length() < 10).collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(shortWordCounts);

        wordList.sort(Comparator.naturalOrder());

    }
}
