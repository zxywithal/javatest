package io.nio17.future;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

public class NIOClient17 {
    private AsynchronousSocketChannel socketChannel;
    private CharBuffer charBuffer;
    private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
    private BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
    public void init() throws IOException, ExecutionException, InterruptedException {
        socketChannel = AsynchronousSocketChannel.open();
        if (socketChannel.isOpen()) {
            socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
            socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            Void connect = socketChannel.connect(new InetSocketAddress("127.0.0.1",8080)).get();
            if (connect != null) {
                throw new RuntimeException("���ӷ�����ʧ��");
            }
        } else {
            throw new RuntimeException("ͨ��δ��");
        }
    }

    public void start() throws Exception {
        System.out.println("�ͻ�������:");
        String input = this.clientInput.readLine();
        Integer integer = socketChannel.write(ByteBuffer.wrap(input.getBytes())).get();
        System.out.println("write integer "+integer);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(buffer).get() != -1) {
            buffer.flip();
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println("������Ӧ:"+charBuffer.toString().trim());
            if (buffer.hasRemaining()) {
                buffer.compact();
            } else {
                buffer.clear();
            }
            System.out.println("�ͻ�������:");
            input = clientInput.readLine();
            integer  = socketChannel.write(ByteBuffer.wrap(input.getBytes())).get();
            System.out.println("write integer "+integer);
        }
    }

    public static void main(String[] args) throws Exception {
        NIOClient17 nio17Client = new NIOClient17();
        nio17Client.init();
        nio17Client.start();
    }

}
