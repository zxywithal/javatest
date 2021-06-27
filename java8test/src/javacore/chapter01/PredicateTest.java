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
        //���ַ���"zxy2" ��predicate ��predicate2 ���߼��� &&
        System.out.println(predicate.and(predicate2).test("zxy2"));
    }
}
