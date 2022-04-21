package com.bus.net.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket server;
    ArrayList<ClientConn> list = new ArrayList<>();
    public ChatServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    // 启动聊天室服务器(等待接收信息)
    public void startServer() throws IOException {
        System.out.println("服务器启动.....");
        while (true) {
            Socket s = server.accept();
            list.add(new ClientConn(s));
            System.out.println("新客户端： " + s.getInetAddress() + ":" + s.getPort());
            System.out.println("客户端总数： " + list.size());
        }
    }

    public static void main(String[] args) throws IOException {
        ChatServer cs = new ChatServer(8888);
        cs.startServer();
    }

    // 客户端连接（内部类），实现多线程
    class  ClientConn implements  Runnable {
        Socket s = null;
        public ClientConn(Socket s) {
            this.s = s;
            (new Thread(this)).start();
        }

        // 发送信息的方法
        public void send(String str) throws IOException {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(str);
        }
        @Override
        public void run() {
            // 客户段发送信息过来时用的是DataOutputStream,所以这里用DataInputStream接收消息
            try {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = dis.readUTF();
                while (str != null && str.length() != 0 ) {
                    System.out.println("客户端：" + str);
                    for (ClientConn cc : list) {
                        if (this != cc) {
                            cc.send(str);
                        }
                    }
                    str = dis.readUTF();
                }
                this.dispose();
            } catch (Exception e) {
                this.dispose();
            }
        }

        // 关闭的方法
        public void dispose() {
            try {
                if(s != null) {
                    s.close();
                }
                list.remove(this);
                System.out.println("一个客户端退出！！");
                System.out.println("客户端总数：" + list.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
