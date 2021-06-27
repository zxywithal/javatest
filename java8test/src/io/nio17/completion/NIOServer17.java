package io.nio17.completion;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


public class NIOServer17 {
    private AsynchronousServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        NIOServer17 ser = new NIOServer17();
        ser.init();
    }

    public void init() throws IOException {
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        if (serverSocketChannel.isOpen()) {
            serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, Boolean.TRUE);
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.accept(null,new ServerCompletionHandler(serverSocketChannel));
        } else {
            throw new RuntimeException("通道未打开");
        }
    }

    class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void>{

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        private CharBuffer charBuffer;
        private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        private AsynchronousServerSocketChannel serverSocketChannel;

        public ServerCompletionHandler(AsynchronousServerSocketChannel serverSocketChannel) {
            this.serverSocketChannel = serverSocketChannel;
        }

        @Override
        public void completed(AsynchronousSocketChannel result, Void attachment) {
            serverSocketChannel.accept(null,this);
            try {
                while (result.read(buffer).get() != -1) {
                    buffer.flip();
                    CharBuffer charBuffer = decoder.decode(buffer);
                    String request = charBuffer.toString().trim();
                    System.out.println("客户端请求 ["+request+"]");
                    String response = "客户端请求 ["+request+"] 已收到";
                    Integer integer = result.write(ByteBuffer.wrap(response.getBytes())).get();
                    System.out.println("write integer "+integer);
                    if (buffer.hasRemaining()) {
                        buffer.compact();
                    } else {
                        buffer.clear();
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }finally {
                try {
                    result.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }

        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            serverSocketChannel.accept(null,this);
            throw new RuntimeException("链接服务失败");
        }
    }
}
