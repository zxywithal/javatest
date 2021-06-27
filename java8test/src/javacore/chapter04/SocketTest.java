package javacore.chapter04;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
    public static void main(String[] args) throws IOException {

//        try (Socket socket = new Socket("time-a.nist.gov", 13);
//             Scanner scanner = new Scanner(socket.getInputStream())){
//            while (scanner.hasNextLine()) {
//                String s = scanner.nextLine();
//                System.out.println(s);
//            }
//        }
        Console console = System.console();
        String chars = String.valueOf(console.readPassword("password:"));
        System.out.println(chars);

    }
}
