package javacore.chapter09.classLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CryptoClassLoader extends ClassLoader {
    private int key;

    public CryptoClassLoader(int key) {
        this.key = key;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String cname = name.replaceAll(".","/")+".caesar";
        String cname = "D:\\javatest\\java8test\\src\\javacore\\chapter09\\Calculator.caesar";
        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(cname));
            for (int i = 0; i < allBytes.length; i++) {
                allBytes[i] = (byte) (allBytes[i]-key);
            }
            Class<?> cl = defineClass(name, allBytes, 0, allBytes.length);
            if(cl==null) throw new ClassNotFoundException(name);
            return  cl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
