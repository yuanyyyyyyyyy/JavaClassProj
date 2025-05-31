package com.example.exp4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ClientChat {
    Scanner sc = new Scanner(System.in);
    Socket socket = null;
    String msg = "";
    
    public static void main(String[] args) throws Exception{
        ClientChat clientChat = new ClientChat();
        // 启动客户端
        clientChat.startApp();
    }

    private void startApp() throws Exception {
        while (true) {
            // 输入用户名
            System.out.println("请输入用户名：");
            String name = sc.nextLine();
            if(name == null){
                System.out.println("用户名不能为空!");
            }else{
                System.out.println("成功登录简易聊天室！");
                // 连接服务端，本代码中模拟网络：采用的是本地网络地址
                socket = new Socket(InetAddress.getLocalHost(), 7778);
                DataOutputStream dos = new DataOutputStream((socket.getOutputStream()));
                // 登录
                dos.writeInt(1);
                dos.writeUTF(name);
                dos.flush();
                // 启动读取消息的客户端线程
                new ClientReader(socket).start();
                // 登录后的客户端可以一直发消息给服务端，正常退出可以使用Ctrl+C退出
                while(true){
                    // 发送消息
                    String msgSend = sc.nextLine();
                    SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println("[你]:" + sDate.format(System.currentTimeMillis()));
                    //非空消息则发送
                    if(!msgSend.trim().isEmpty()){
                        dos.writeInt(2);
                        dos.writeUTF(msgSend);
                        dos.flush();
                    }            
                }
            }
        }
    }
}