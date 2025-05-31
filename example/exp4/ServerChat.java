package com.example.exp4;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 简易聊天室服务端
 */
public class ServerChat {

    //客户端列表
    public static Map<Socket, String> onLineSockets  = new HashMap<>();

    public static void main(String[] args) {
        try{
            //创建服务端对象，在7778端口监听
            ServerSocket serverSocket = new ServerSocket(7778);
            // 循环监听客户端连接，阻塞状态accept
            while(true){
                Socket socket = serverSocket.accept(); //服务器阻塞，等待客户端连接
                // 启动一个线程，来处理客户端的请求
                new ServerReader(socket).start();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}