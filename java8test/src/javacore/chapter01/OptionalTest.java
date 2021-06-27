package javacore.chapter01;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class OptionalTest {
    public static void main(String[] args) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("cities.txt");
        URI uri = url.toURI();
        byte[] bytes = Files.readAllBytes(Paths.get(uri));
        String contents = new String(bytes, StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        Optional<String> fred = wordList.stream().filter(s -> s.contains("fred")).findFirst();
        System.out.println(fred.orElse("no word")+" contains fred");

        fred.ifPresent(s-> System.out.println(s+ " contains Fred"));

        Set<String> set = new HashSet<>();
        Optional<Boolean> aBoolean = fred.map(set::add);
        System.out.println("set size "+set.size());

        Optional<Object> empty = Optional.empty();
        System.out.println("result "+ empty.orElse("N/A"));
        System.out.println("result "+empty.orElseGet(() -> Locale.getDefault().getDisplayName()));

    }
}
