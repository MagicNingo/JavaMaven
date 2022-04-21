package com.bus.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpTow {

    public static void main(String[] args) throws IOException {
       /* // 1.创建DatagramPacket(用来接收数据包)
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        // 2.创建DatagramSocket对象，绑定端口号，要发送端一致；实现接收数据包receive()
        DatagramSocket socket = new DatagramSocket(8200);
        boolean f = true;
        Scanner input = new Scanner(System.in);
        while (f) {
            //发送消息
            System.out.println("本机: ");
            String msg = input.nextLine();
            if ("exit".equalsIgnoreCase(msg)) {
                break;
            }
            byte[] buf = msg.getBytes();
            InetAddress addresses = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf, buf.length,addresses,8100);
            socket.send(packet);

            //接收消息
            System.out.println("等待消息：");
            socket.receive(datagramPacket);
            String str = new String(bytes, 0, datagramPacket.getLength());
            // 3.拆包(把数据从数据包解析出来)
            InetAddress address = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            System.out.println("来自于" + address + "-->" + "port" + port + "的消息: " + str);


        }
        // 4.关闭资源
        socket.close();*/

        MyDatagramPacket myDatagramPacket = new MyDatagramPacket(8200);
        while (myDatagramPacket.isF()) {
            myDatagramPacket.send(8100);

            myDatagramPacket.receive();
        }
        myDatagramPacket.close();
    }
}
