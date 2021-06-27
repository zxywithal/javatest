package javacore.chapter02.randomAccess;

import java.io.*;

public class DataIO {

    public static void writeFixedString(String s, int size, DataOutput output) throws IOException {
        char ch = 0;
        for (int i = 0; i < size; i++) {
            if (i < s.length())
                ch = s.charAt(i);
            else
                ch = 0;
            output.writeChar(ch);
        }
    }

    public static String readFixedString(int size, DataInput input) throws IOException {
        StringBuilder sb = new StringBuilder(size);
        boolean more = true;
        int i = 0 ;
        for (; i < size && more; i++) {
            char ch = input.readChar();
            if (ch == 0)
                more = false;
            else
                sb.append(ch);
        }
        input.skipBytes(2 * (size - i));
        return sb.toString();
    }

    public  static Object clone(Object obj) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
        }
        ;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

}
