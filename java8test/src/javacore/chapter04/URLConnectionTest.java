package javacore.chapter04;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {
    public static void main(String[] args) throws Exception {
        String urlName;
        if(args.length>0){
            urlName = args[0];
        }else{
            urlName = "http://wwww.baidu.com";
        }
        URL url = new URL(urlName);
        URLConnection urlConnection = url.openConnection();
        if (args.length > 2) {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            String encodeString = Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
            urlConnection.setRequestProperty("Authorization","Basic"+encodeString);
        }
        urlConnection.connect();
        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
        headerFields.forEach((key,value)->{
            value.forEach(listValue->{
                System.out.println(key +":"+listValue);
            });
        });
        //print convenience functions
        System.out.println("=========================");
        System.out.println("getContentType "+urlConnection.getContentType());
        System.out.println("getContentLengthLong "+urlConnection.getContentLengthLong());
        System.out.println("getContentEncoding "+urlConnection.getContentEncoding());
        System.out.println("getDate "+urlConnection.getDate());
        System.out.println("getExpiration "+urlConnection.getExpiration());
        System.out.println("getLastModified "+urlConnection.getLastModified());
        System.out.println("=====================");
        String enCoding = urlConnection.getContentEncoding();
        if (enCoding == null) {
            enCoding = StandardCharsets.UTF_8.toString();
        }
        try (Scanner scanner = new Scanner(urlConnection.getInputStream(), enCoding)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }

    }
}
