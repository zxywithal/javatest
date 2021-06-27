package javacore.chapter02;

import java.io.*;
import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter(System.out,true);
        writer.println(Charset.defaultCharset());
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String buffer = null;
            while (!(buffer=reader.readLine()).equals("exit")){
                writer.println(buffer);
            }

        }

    }
}
