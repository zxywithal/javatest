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
        //��ʼ���ͻ���socket
        SocketChannel channel = SocketChannel.open();
        //��ʼ��selector
        Selector s = Selector.open();
        channel.configureBlocking(Boolean.FALSE);
        //��һ��ע���������¼�����������һֱ��������״̬
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
                        //���û��������һ�䣬�����NotYetConnectedException Ӧ�û�û�����ʵ�ʵ�����
                        socketChannel.finishConnect();
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        allocate.put("client connectable".getBytes(StandardCharsets.UTF_8));
                        allocate.flip();
                        socketChannel.write(allocate);
                        //��Գ����Ӳ��ܲ���shutdownOutput�������write���� ���shutdownOutput��closeû������
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
                        //��Գ����Ӳ��ܲ���shutdownOutput�������write���� ���shutdownOutput��closeû������
//                        socketChannel.shutdownOutput();
                        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
                    }

                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        int read = socketChannel.read(allocate);
                        System.out.println("�Ƿ��ȡ���� [" + read + "]");
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
