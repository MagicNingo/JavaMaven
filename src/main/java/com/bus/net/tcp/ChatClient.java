package com.bus.net.tcp;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    Socket s = null;
    // 以线程方式启动一个客户端
    public  ChatClient() throws IOException {
       s = new Socket("127.0.0.1",8888);
        (new Thread(new ReceiverThread())).start();
    }

    public void send(String str) throws IOException {
        //发送信息用DataOutPutStream 对象
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeUTF(str);
    }

    public void disconnect() throws IOException {
        s.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ChatClient client = new ChatClient();
        System.out.println("请输入：");
        String str = br.readLine();

        while (str != null && str.length() !=0) {
            client.send(str);
            System.out.println("请输入：");
            str = br.readLine();
            System.out.println("自己说：" + str);
        }
        client.disconnect();
    }

    // 接收线程，内部类
    class ReceiverThread implements Runnable {

        @Override
        public void run() {
            if (s == null) {
                return;
            }
            try {
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = dis.readUTF();

                while (str != null && str.length() != 0) {
                    System.out.println("别人：" + str);
                    str = dis.readUTF();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
