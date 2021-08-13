package org.example;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {

    public static void main(String[] args) {

        try {
            // Определяем сокет сервера
            InetSocketAddress socketAddress = new InetSocketAddress(App.HOSTNAME,
                    App.PORT);
            final SocketChannel socketChannel = SocketChannel.open();
            // подключаемся к серверу
            socketChannel.connect(socketAddress);

            // Определяем буфер для получения данных
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
            String msg;
            while (true) {
                System.out.println("Enter message for server...");
                msg = App.consoleReadString();
                if ("end".equalsIgnoreCase(msg)) break;
                socketChannel.write(
                        ByteBuffer.wrap(
                                msg.getBytes(StandardCharsets.UTF_8)));
                Thread.sleep(2000);
                int bytesCount = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0, bytesCount,
                        StandardCharsets.UTF_8).trim());
                inputBuffer.clear();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
