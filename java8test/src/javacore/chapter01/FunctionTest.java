package javacore.chapter01;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        FunctionalInterface<String,Integer> convert01 = from->{
            return Integer.parseInt(from);
        };

        FunctionalInterface<String,Integer> convert02 = from->Integer.parseInt(from);

        FunctionalInterface<String,Integer> convert03 = Integer::valueOf;

        //��stringת��int
        Function<String,Integer> toInteger = Integer::parseInt;
        System.out.println(toInteger.apply("12345"));
        //�Ƚ�stringת��int Ȼ�����andthen ��ת��string
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123");
        //�Ƚ�stringת��int Ȼ�����andthen ��ת��string �ڵ���andthen ��ȡ�ַ����ĵ�һλ
        Function<String, Character> afterToStartsWith = backToString.andThen(a->{
            return a.toCharArray()[0];
        });
        System.out.println(afterToStartsWith.apply("98754"));
    }
}
