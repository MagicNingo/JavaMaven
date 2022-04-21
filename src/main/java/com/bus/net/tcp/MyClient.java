package com.bus.net.tcp;

import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        // 准备好两条管道
        OutputStream out = null;
        DataOutputStream dos = null;
        InputStream in = null;
        DataInputStream dis = null;

        //得到键盘输入
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        //创建一个客户端的Socket
        // Socket对象一创建，就会连接上指代那个的ip地址和端口号
        //Socket socket = new Socket("127.0.0.1", 8888);
        //和服务器打招呼，要先建立输出流、
        /*OutputStream outputStream = socket.getOutputStream();
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeUTF("你好！");
        out.flush();
        out.close();*/
        Socket socket = new Socket("127.0.0.1", 8888);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        // 开始聊天
        while(true) {
            System.out.println("你输入：");
            dos.writeUTF(br.readLine());
            // 接受服务器的说话
            String str = null;
            if ((str = dis.readUTF()) != null) {
                if ("exit".equalsIgnoreCase(str)) {
                    break;
                }
                System.out.println("服务器说：" + str);
            }
        }
        dis.close();
        dos.close();
        System.out.println("会话结束.....");
    }
}
