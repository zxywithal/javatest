public class Test2 {
    public static void main(String[] args) {
            int i;
            Object key = new Object();
        System.out.println((i = key.hashCode())^(i>>>16));

    }
}
