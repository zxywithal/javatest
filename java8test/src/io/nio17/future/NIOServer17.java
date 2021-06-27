package io.nio17.future;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.*;

public class NIOServer17 {
    private ExecutorService taskExecutor;
    private AsynchronousServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        NIOServer17 ser = new NIOServer17();
        ser.init();
        ser.start();
    }

    public void init() throws IOException {
        taskExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        if (serverSocketChannel.isOpen()) {
            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, Boolean.TRUE);
            serverSocketChannel.bind(new InetSocketAddress(8080));
        } else {
            throw new RuntimeException("通道未打开");
        }
    }
    public void start() {
        while (true) {
            System.out.println("等待客户端");
            Future<AsynchronousSocketChannel> accept = serverSocketChannel.accept();
            AsynchronousSocketChannel socketChannel = null;
            try {
                socketChannel = accept.get();
                taskExecutor.submit(new Worker(socketChannel));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e);
                System.err.println("服务器关闭");
                taskExecutor.shutdown();
                while (!taskExecutor.isTerminated()) {

                }

            }

        }
    }





    class Worker implements Callable<String>{
        private CharBuffer charBuffer;
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        private AsynchronousSocketChannel socketChannel;

        public Worker(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public String call() throws Exception {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer).get() != -1) {
                buffer.flip();
                CharBuffer charBuffer = decoder.decode(buffer);
                String request = charBuffer.toString().trim();
                System.out.println("客户端请求 ["+request+"]");
                String response = "客户端请求 ["+request+"] 已收到";
                Integer integer = socketChannel.write(ByteBuffer.wrap(response.getBytes())).get();
                System.out.println("write integer "+integer);
                if (buffer.hasRemaining()) {
                    buffer.compact();
                } else {
                    buffer.clear();
                }
            }
            socketChannel.close();
            return "ok";
        }

    }
}
