package javacore.chapter04;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(8819);
             Socket accept = ss.accept()) {
            try (Scanner scanner = new Scanner(accept.getInputStream());
                 PrintWriter writer = new PrintWriter(accept.getOutputStream(),true)) {
                writer.println("Hello! Enter bye to exit");
                boolean bye = false;
                while (!bye && scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    writer.println("Echo :" + s);
                    if(s.trim().equals("bye")) bye = true;
                }
            }

        }
    }
}
