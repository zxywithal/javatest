package io.nio14;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * jdk 1.4 版本的NIO
 */
public class NIOServer14 {
    private static Selector selector;

    private static void init(){
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(Boolean.FALSE);
            serverSocketChannel.bind(new InetSocketAddress(9000));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listen(){
        while(true){
            try {
                //select 阻塞的
                int select = selector.select();
//                System.out.println("select 方法返回值["+select+"]");
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    handleRequest(selectionKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void handleRequest(SelectionKey selectionKey) throws IOException {

        SocketChannel socketChannel = null;
            try {
                if (selectionKey.isAcceptable()) {
                    System.out.println(selectionKey.toString()+"is acceptable");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("接收新的channel "+socketChannel);
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()) {
//                    System.out.println(selectionKey.toString()+"is readable");
                    socketChannel = (SocketChannel)selectionKey.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(byteBuffer);
//                    System.out.println("获取数据 ["+count+"]");
                    if (count > 0) {
                        System.out.println("服务端接收到的数据 ["+new String(byteBuffer.array(),0,byteBuffer.position())+"]");
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_WRITE);
                    }
//                    else if (count == -1) {
//                        //表明客户端流已经关闭
//                        System.out.println("客户端流已关闭，关闭当前流");
//                        socketChannel.close();
//                    }
                }else if (selectionKey.isWritable()) {
//                    System.out.println(selectionKey.toString()+"is writable");
                    socketChannel = (SocketChannel)selectionKey.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.put("收到".getBytes(StandardCharsets.UTF_8));
                    allocate.flip();
                    socketChannel.write(allocate);
                    //面对长连接不能不能shutdownOutput，如果是write操作 这个shutdownOutput和close没有区别
//                    socketChannel.shutdownOutput();
//                    socketChannel.close();
                    //如果没有上面一句的关闭，保留下面的注册可读事件 使用场景应该是我处理长链接的情况，因为如果当前链接 注册可读事件后，客户端关闭流会发送-1数据，服务端会按流程目前处于可读状态处理
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }else{
                    System.out.println(selectionKey.toString()+"不知道啥情况");
                }
            } catch (IOException e) {
                if (socketChannel!=null) {
                    socketChannel.close();
                }
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        init();
        listen();
    }
}
