package io.nio17.completion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class NIOClient17 {

    class ClientCompletionHandler implements CompletionHandler<Void, Void> {
        private BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));
        private AsynchronousSocketChannel socketChannel;
        private CharBuffer charBuffer;
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

        public ClientCompletionHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Void result, Void attachment) {
            try {
                System.out.println("客户端输入:");
                String input = this.clientInput.readLine();
                Integer integer = socketChannel.write(ByteBuffer.wrap(input.getBytes())).get();
                System.out.println("write integer "+integer);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (socketChannel.read(buffer).get() != -1) {
                    buffer.flip();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    System.out.println("服务响应:"+charBuffer.toString().trim());
                    if (buffer.hasRemaining()) {
                        buffer.compact();
                    } else {
                        buffer.clear();
                    }
                    System.out.println("客户端输入:");
                    input = clientInput.readLine();
                    integer  = socketChannel.write(ByteBuffer.wrap(input.getBytes())).get();
                    System.out.println("write integer "+integer);
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }

        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            throw new RuntimeException("链接服务失败");
        }
    }



    public void start() throws Exception {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        if (socketChannel.isOpen()) {
            socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
            socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080), null, new ClientCompletionHandler(socketChannel));
            while (Boolean.TRUE) {
                Thread.sleep(5000);
            }
        } else {
            throw new RuntimeException("通道未打开");
        }
    }

    public static void main(String[] args) throws Exception {
        NIOClient17 nio17Client = new NIOClient17();
        nio17Client.start();
    }

}
