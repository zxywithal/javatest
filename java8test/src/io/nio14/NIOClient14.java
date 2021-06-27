package io.nio14;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NIOClient14 {
    public static void main(String[] args) throws IOException {
        //初始化客户端socket
        SocketChannel channel = SocketChannel.open();
        //初始化selector
        Selector s = Selector.open();
        channel.configureBlocking(Boolean.FALSE);
        //第一个注册我连接事件，负责程序会一直卡在连接状态
//            channel.register(s, SelectionKey.OP_WRITE);
        channel.register(s, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(9000));

        while (true) {
            try {
                Thread.sleep(5000);
                int count = s.select();
                Set<SelectionKey> selectionKeys = s.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isConnectable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        //如果没有下面这一句，会出现NotYetConnectedException 应该还没有完成实际的连接
                        socketChannel.finishConnect();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.put("client connectable".getBytes(StandardCharsets.UTF_8));
                        allocate.flip();
                        socketChannel.write(allocate);
                        //面对长连接不能不能shutdownOutput，如果是write操作 这个shutdownOutput和close没有区别
//                        socketChannel.shutdownOutput();
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                    }
                    if (selectionKey.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.put("client writable".getBytes(StandardCharsets.UTF_8));
                        allocate.flip();
                        socketChannel.write(allocate);
                        //面对长连接不能不能shutdownOutput，如果是write操作 这个shutdownOutput和close没有区别
//                        socketChannel.shutdownOutput();
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                    }

                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(allocate);
                        System.out.println("是否获取数据 [" + read + "]");
                        if (read > 0) {
                            System.out.println("[" + Thread.currentThread().getName() + "] receiver message is [" + new String(allocate.array(), 0, allocate.position(), StandardCharsets.UTF_8) + "]");
                        }
//                        Thread.sleep(5000);
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_WRITE);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
