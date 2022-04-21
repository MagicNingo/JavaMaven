package com.bus.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        OutputStream out = null;
        DataOutputStream dos = null;
        InputStream in = null;
        DataInputStream dis = null;

        //得到键盘输入
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);


        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务器启动.....");
        // accept()方法是阻塞式的
        Socket accept = serverSocket.accept();
        while (true) {
            /*System.out.println("一个客户端连接上来了....");
            InputStream inputStream = accept.getInputStream();
            DataInputStream in = new DataInputStream(inputStream);
            String msg = in.readUTF();
            System.out.println("客户端说：" + msg);*/
            in = accept.getInputStream();
            out = accept.getOutputStream();
            dis = new DataInputStream(in);
            dos = new DataOutputStream(out);
            String str = null;
            if ((str = dis.readUTF()) != null) {
                if ("exit".equalsIgnoreCase(str)) {
                    break;
                }
                System.out.println("From：" + accept.getInetAddress() + ",port:" + accept.getPort() + "-->" +str);
            }
            System.out.println("你说：");
            dos.writeUTF(br.readLine());
        }
        dis.close();
        dos.close();
        System.out.println("服务器关闭....");

    }
}
