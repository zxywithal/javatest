package javacore.chapter01;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DownstreamCollectors {

    public static class City
    {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population)
        {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName()
        {
            return name;
        }

        public String getState()
        {
            return state;
        }

        public int getPopulation()
        {
            return population;
        }
    }

    public static void main(String[] args) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("cities.txt");
        Path path = Paths.get(url.toURI());
        Stream<String> lines = Files.lines(path);
        //用两次map操作，把txt中的数据转换成city stream
        Stream<City> cityStream = lines.map(s -> s.split(",")).map(s -> new City(s[0], s[1], Integer.valueOf(s[2].trim())));

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        //countryTset
        Map<String, Set<Locale>> countryToSet = locales.collect(Collectors.groupingBy(Locale::getDisplayName, Collectors.toSet()));
        System.out.println("countryToLocaleSet: " + countryToSet);
        Iterator<String> iterator = countryToSet.keySet().iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
            if (countryToSet.get(next).size()>1) {
                System.out.println(countryToSet.get(next));
            }
        }

        //按照state 分组计算population
        Map<String, Integer> stateToCityPopulation = cityStream.collect(Collectors.groupingBy(City::getState, Collectors.summingInt(City::getPopulation)));
        System.out.println(stateToCityPopulation);

        //按照state 分组 按照把每个组的国家名称用逗号分隔
        Stream<String> lines1 = Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("cities.txt").toURI()));
        Stream<City> cityStream1 = lines1.map(s -> s.split(",")).map(s -> new City(s[0], s[1], Integer.valueOf(s[2].trim())));
        Map<String, String> stateToCityNames = cityStream1.collect(Collectors.groupingBy(City::getState, Collectors.mapping(City::getName, Collectors.joining(","))));
        System.out.println("stateToCityNames "+stateToCityNames);


        //按照国家分组，把每个国家对应的语言转化成set
        locales = Stream.of(Locale.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));
        System.out.println("countryToLanguages "+countryToLanguages);
    }
}
