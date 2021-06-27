package javacore.chapter06;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TimeLine {
    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgrithm();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.printf("%d milliseconds\n",timeElapsed.toMillis());

        Instant start2 = Instant.now();
        runAlgrithm2();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        System.out.printf("%d milliseconds\n",timeElapsed2.toMillis());
        boolean overTenTimesFaster = timeElapsed.multipliedBy(10).minus(timeElapsed2).isNegative();
        System.out.printf("The first algorithm is %s more than ten times faster",overTenTimesFaster?"":"not");
    }
    public static void runAlgrithm() {
        List<Integer> collect = new Random().ints().map(i -> i % 100).limit(100).boxed().collect(Collectors.toList());
        Collections.sort(collect);
        System.out.println(collect);
    }
    public static void runAlgrithm2() {
        List<Integer> list = new Random().ints().map(i -> i % 100).limit(100).boxed().collect(Collectors.toList());
        while (!(IntStream.range(1, list.size()).allMatch(i -> list.get(i - 1).compareTo(list.get(i)) <= 0))) {
            Collections.shuffle(list);
        }
        System.out.println(list);
    }
}
