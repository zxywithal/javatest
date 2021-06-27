package javacore.chapter04.interruptible;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class InterruptibleSocketFrame extends JFrame {

    private Scanner in;
    private JTextArea messages;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame() {
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        final int TEXT_ROWS = 20;
        final int TEXT_COLUMNS = 60;
        messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");
        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);
        northPanel.add(cancelButton);
        interruptibleButton.addActionListener(event ->
        {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() ->
            {
                try
                {
                    connectInterruptibly();
                }
                catch (IOException e)
                {
                    messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
                }
            });
            connectThread.start();
        });

        blockingButton.addActionListener(event ->
        {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() ->
            {
                try
                {
                    connectBlocking();
                }
                catch (IOException e)
                {
                    messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
                }
            });
            connectThread.start();
        });


        cancelButton.addActionListener(event ->
        {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
        });
        server = new TestServer();
        new Thread(server).start();
        pack();
    }

    public void connectInterruptibly() throws IOException {
        messages.append("Interruptible:\n");
        try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8819))){
            in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
            System.out.println(Thread.currentThread().isInterrupted());
        }finally {
            EventQueue.invokeLater(()->{
                messages.append("Channel closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }

    public void connectBlocking() throws IOException {
        messages.append("Blocking:\n");
        try(Socket socket = new Socket("localhost",8819)){
            in = new Scanner(socket.getInputStream(),"utf-8");
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }finally {
            System.out.println(Thread.currentThread().isInterrupted());
            EventQueue.invokeLater(()->{
                messages.append("socket closed\n");
                interruptibleButton.setEnabled(true);
                blockingButton.setEnabled(true);
            });
        }
    }
    class TestServer implements Runnable {
        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(8819)) {
                while (true){
                    Socket accept = serverSocket.accept();
                    Thread thread = new Thread(new TestServerHandler(accept));
                    thread.start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run:"+e);
            } ;
        }
    }


    class TestServerHandler implements Runnable{
        private Socket socket;
        private int counter;

        public TestServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                try{
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
                    while (counter < 100) {
                        counter=counter+1;
                        if (counter <= 10) {
                            writer.println(counter);
                        }
                        Thread.sleep(100);
                        System.out.println("counter "+counter);
                    }
                }finally {
                    socket.close();
                }
            } catch (Exception e) {
                messages.append("\nTestServerHandler.run:"+e);
            }
        }
    }
}
