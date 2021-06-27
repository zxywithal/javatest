import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

public class MethodHandleTest {
    public static void main(String[] args) throws Throwable {
        Object x, y;
        String s;
        int i;
        //获取lookup
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        //定义方法类型
        MethodType mt = MethodType.methodType(String.class, char.class, char.class);
        //获取方法句柄
        MethodHandle mh = lookup.findVirtual(String.class, "replace", mt);
        //根据精确类型执行方法
        s = (String) mh.invokeExact("daddy",'d','n');
        System.out.println("============"+s);
        //执行方法
        s = (String) mh.invokeWithArguments("daddy",'d','n');
        System.out.println("invokeWithArguments ============"+s);

        mt = MethodType.methodType(List.class, Object[].class);
        mh = lookup.findStatic(Arrays.class, "asList", mt);
        System.out.println(mh.isVarargsCollector());
        x = mh.invoke("one", "tow");
        System.out.println(x);
        mt = MethodType.genericMethodType(3);        System.out.println(mt);

    }
}
