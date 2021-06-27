package javacore.chapter09.hash;

import javax.xml.bind.DatatypeConverter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

public class Digest {
    public static void main(String[] args) throws Exception {
        String algName = args.length == 2 ? args[1] : "SHA-256";
        MessageDigest messageDigest = MessageDigest.getInstance(algName);
        byte[] allBytes = Files.readAllBytes(Paths.get(args[0]));
        messageDigest.update(allBytes);
        byte[] digest = messageDigest.digest();
        // TODO: 2020/3/31 DatatypeConverter 这个类很有用
        System.out.println(DatatypeConverter.printBase64Binary(digest));
    }
}
