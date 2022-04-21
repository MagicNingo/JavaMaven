package com.bus.net.udp;

import java.io.IOException;
import java.net.*;

public class UdpSend {

    public static void main(String[] args) throws IOException {

        byte[] buf = "你好！".getBytes();
        // 1.创建DatagramPacket对象，封装数据，接收地址和端口号
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length,address,8100);

        // 2.创建DatagramSocket对象，调用send()方法，发送数据包
        DatagramSocket socket = new DatagramSocket();
        socket.send(datagramPacket);

        // 3.关闭资源
        socket.close();
        System.out.println("--- send ok ---");
    }
}
