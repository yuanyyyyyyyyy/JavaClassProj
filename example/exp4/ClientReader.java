package com.example.exp4;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * 简易聊天室客户端信息处理线程
 */
public class ClientReader extends Thread {

    private Socket socket;

    public ClientReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 循环等待服务端的消息
            while (true) {
                String msg = dis.readUTF();
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}