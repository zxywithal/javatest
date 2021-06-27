package io.nio14;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * jdk 1.4 �汾��NIO
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
            System.out.println("�������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listen(){
        while(true){
            try {
                //select ������
                int select = selector.select();
//                System.out.println("select ��������ֵ["+select+"]");
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
                    System.out.println("�����µ�channel "+socketChannel);
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()) {
//                    System.out.println(selectionKey.toString()+"is readable");
                    socketChannel = (SocketChannel)selectionKey.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int count = socketChannel.read(byteBuffer);
//                    System.out.println("��ȡ���� ["+count+"]");
                    if (count > 0) {
                        System.out.println("����˽��յ������� ["+new String(byteBuffer.array(),0,byteBuffer.position())+"]");
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_WRITE);
                    }
//                    else if (count == -1) {
//                        //�����ͻ������Ѿ��ر�
//                        System.out.println("�ͻ������ѹرգ��رյ�ǰ��");
//                        socketChannel.close();
//                    }
                }else if (selectionKey.isWritable()) {
//                    System.out.println(selectionKey.toString()+"is writable");
                    socketChannel = (SocketChannel)selectionKey.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    allocate.put("�յ�".getBytes(StandardCharsets.UTF_8));
                    allocate.flip();
                    socketChannel.write(allocate);
                    //��Գ����Ӳ��ܲ���shutdownOutput�������write���� ���shutdownOutput��closeû������
//                    socketChannel.shutdownOutput();
//                    socketChannel.close();
                    //���û������һ��Ĺرգ����������ע��ɶ��¼� ʹ�ó���Ӧ�����Ҵ������ӵ��������Ϊ�����ǰ���� ע��ɶ��¼��󣬿ͻ��˹ر����ᷢ��-1���ݣ�����˻ᰴ����Ŀǰ���ڿɶ�״̬����
                    socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }else{
                    System.out.println(selectionKey.toString()+"��֪��ɶ���");
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
