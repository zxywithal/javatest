package javacore.chapter09.classLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Caesar {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("USAGE:java class loader.Caesar in out key");
            return;
        }
        try(FileInputStream in = new FileInputStream(args[0]);
            FileOutputStream out = new FileOutputStream(args[1])){
            int key = Integer.parseInt(args[2]);
            int ch;
            while ((ch = in.read()) != -1) {
                out.write((byte) (ch+key));
            }
        }
    }
}
