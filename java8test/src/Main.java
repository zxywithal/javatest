import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] split = "3214321~|456".split("~\\|");

        String rep = "^([a-z0-9A-Z---_]+[_|\\.-]?)+[a-z0-9A-Z---_]@([a-z0-9A-Z--]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z--]{2,}$";
//        String rep = "^[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)*[a-zA-Z0-9_-]{0,}@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9-]{2,}$";
        System.out.println(rep);
        Pattern compile = Pattern.compile(rep);
        System.out.println("zxy@AA.-- " + compile.matcher("zxy@AA.--").matches());
        System.out.println("zxy@AA.--com " + compile.matcher("zxy@AA.--com").matches());
        System.out.println("zxy@AA.-- " + compile.matcher("zxy@AA.--").matches());
        System.out.println("zxy@163.com " + compile.matcher("zxy@163.com").matches());
        System.out.println("test.test-1st2_test3@hotmail.com " + compile.matcher("test.test-1st2_test3@hotmail.com").matches());
        System.out.println("test..test-1st2_test3@hotmail.com " + compile.matcher("test..test-1st2_test3@hotmail.com").matches());
        System.out.println("test--test.1st2.test3@hotmail.com " + compile.matcher("test--test.1st2.test3@hotmail.com").matches());
        System.out.println("test__test.1st2.test3@hotmail.com " + compile.matcher("test__test.1st2.test3@hotmail.com").matches());
        System.out.println("test-test-1st2-test.test3@hotmail.com " + compile.matcher("test-test-1st2-test.test3@hotmail.com").matches());
        System.out.println(".test-test-1st2-test.test3@hotmail.com " + compile.matcher(".test-test-1st2-test.test3@hotmail.com").matches());
        System.out.println("test-test-1st2-test.test3.@hotmail.com " + compile.matcher("test-test-1st2-test.test3.@hotmail.com").matches());
        System.out.println("-test-test-1st2-test.test3@hotmail.com " + compile.matcher("-test-test-1st2-test.test3@hotmail.com").matches());
        System.out.println("test-test-1st2-test.test3-@hotmail.com " + compile.matcher("test-test-1st2-test.test3-@hotmail.com").matches());
        System.out.println("_test-test-1st2-test.test3@hotmail.com " + compile.matcher("_test-test-1st2-test.test3@hotmail.com").matches());
        System.out.println("test-test-1st2-test.test3_@hotmail.com " + compile.matcher("test-test-1st2-test.test3_@hotmail.com").matches());
        System.out.println("zxy@163.com         " + compile.matcher("zxy@163.com").matches());
        System.out.println("zxy@163..com        " + compile.matcher("zxy@163..com").matches());
        System.out.println("zxy@163.com.cn      " + compile.matcher("zxy@163.com.cn").matches());
        System.out.println("zxy@163.com.cn      " + compile.matcher("zxy@163.com.cn").matches());
        System.out.println("zxy@163-178.com.cn  " + compile.matcher("zxy@163-178.com.cn").matches());
        System.out.println("zxy@163-178.com.cn  " + compile.matcher("zxy@163-178.com.cn").matches());
        System.out.println("zxy@163-178--com.cn " + compile.matcher("zxy@163-178--com.cn").matches());
        System.out.println("zxy@163-178--com.cn-- " + compile.matcher("zxy@163-178--com.cn--").matches());
        System.out.println("zxy@-163178--com.cn-- " + compile.matcher("zxy@-163178--com.cn--").matches());
        System.out.println("zxy@163178--com.cn- " + compile.matcher("zxy@-163178--com.cn-").matches());
        System.out.println("zxy@163..178.com.cn " + compile.matcher("zxy@163..178.com.cn").matches());
        System.out.println("zxy@163178..com.cn  " + compile.matcher("zxy@163178..com.cn").matches());
        System.out.println("zxy@163178.com.cn   " + compile.matcher("zxy@163178.com.cn").matches());
        System.out.println("zxy@163178.com.cn.  " + compile.matcher("zxy@163178.com.cn.").matches());
        System.out.println("zxy@163178.com.cn.. " + compile.matcher("zxy@163178.com.cn..").matches());
        System.out.println("zxy@163178comcn     " + compile.matcher("zxy@163178comcn").matches());
    }


    public static String convertToDog(Dog dog){
        return "DOG'd name is : " + dog.getName();
    }
    public static String convertToCat(){
        return "CAT";
    }
}
