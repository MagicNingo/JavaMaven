package com.bus.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpReceive {

    public static void main(String[] args) throws IOException {
        // 1.创建DatagramPacket(用来接收数据包)
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        // 2.创建DatagramSocket对象，绑定端口号，要发送端一致；实现接收数据包receive()
        DatagramSocket socket = new DatagramSocket(8100);
        socket.receive(datagramPacket);

        // 3.拆包(把数据从数据包解析出来)
        InetAddress address = datagramPacket.getAddress();
        System.out.println("地址：" + address);
        int port = datagramPacket.getPort();
        System.out.println("端口号：" + port);
        int length = datagramPacket.getLength();
        System.out.println("数据长度：" + length);
        byte[] data = datagramPacket.getData();
        System.out.println("得到的消息：" + new String(data,0,length));
        // 4.关闭资源
        socket.close();
    }
}
