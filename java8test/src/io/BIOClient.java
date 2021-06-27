package io;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            socket.shutdownOutput();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream()),StandardCharsets.UTF_8));
            String s = inputStream.readLine();
            System.out.println("[" + Thread.currentThread().getName() + "] ’µΩœÏ”¶£∫" + s);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
