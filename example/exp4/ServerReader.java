package com.example.exp4;
/**
 * 简易聊天室服务端信息处理线程
 * 1.登录聊天室
 * 2.对所有人发送消息
 */

 import java.io.DataInputStream;
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.net.Socket;
 import java.text.SimpleDateFormat;
 import java.util.Set;
 
 public class ServerReader extends Thread {
     private Socket socket; // 客户端socket
 
     public ServerReader(Socket socket) {
         this.socket = socket;
     }
 
     @Override
     public void run() {
         DataInputStream dis = null;
 
         try {
             // 从客户端的Socket中获取输入流，即客户端发送过来的信息
             dis = new DataInputStream(socket.getInputStream());
             while (true) {
                 // 业务状态：使用数字1表示登录 2表示发送消息
                 int flag = dis.readInt();
                 if (flag == 1) { // 登录业务
                     // 聊天登录的用户名
                     String name = dis.readUTF();
                     // 加入到聊天用户在线列表
                     ServerChat.onLineSockets.put(socket, name);
                     // 发送上线通知 -〉与对所有人发送消息功能一致
                     sendMsgToAll(socket, name + "上线了");
                 } else { // 发送消息
                     // 消息内容
                     String newMsg = dis.readUTF();
                     // 发送消息的人
                     String sendName = ServerChat.onLineSockets.get(socket);
                     // 完整的消息内容
                     StringBuilder msg = new StringBuilder();
                     msg.append(" " + newMsg);
                     msg.append("[" + sendName + "]:");
                     // 发送消息的时间
                     SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                     msg.append(sDate.format(System.currentTimeMillis()));
                     // 对所有人发送消息
                     sendMsgToAll(socket, msg.toString());
                 }
             }
         } catch (Exception e) {
             // 客户端Socket异常，则移除客户端
             ServerChat.onLineSockets.remove(socket);
         }
     }
 
     /**
      * 发送消息到所有客户端
      * 
      * @throws IOException
      */
     private void sendMsgToAll(Socket socket, String msg) throws IOException {
         // 获取所有客户端的Socket
         Set<Socket> allClientSockets = ServerChat.onLineSockets.keySet();
         // 遍历客户端发送消息
         for (Socket sk : allClientSockets) {
             if (sk != socket) { // 排除消息发送者
                 DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
                 dos.writeUTF(msg);
                 dos.flush();
             }
         }
     }
 
 }