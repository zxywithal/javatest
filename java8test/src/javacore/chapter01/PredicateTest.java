package javacore.chapter01;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = s->s.length()>0;
        Predicate<String> predicate2 = s->s.equals("zxy");
        Predicate<String> predicate3 = Objects::isNull;
        Predicate<String> predicate4 = Objects::nonNull;
        System.out.println(predicate.test("zxy"));
        //对字符串"zxy2" 求predicate 和predicate2 的逻辑与 &&
        System.out.println(predicate.and(predicate2).test("zxy2"));
    }
}
