package com.bus.net.udp;

import lombok.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

@Data
public class MyDatagramPacket {
    // 1.创建DatagramPacket(用来接收数据包)
    byte[] bytes ;
    DatagramPacket datagramPacket;
    // 2.创建DatagramSocket对象，绑定端口号，要发送端一致；实现接收数据包receive()
    DatagramSocket socket;
    boolean f = true;
    public MyDatagramPacket(int port) {
        this.bytes = new byte[1024];
        this.datagramPacket = new DatagramPacket(bytes,bytes.length);
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void send(int port) {
        Scanner input = new Scanner(System.in);
        //发送消息
        System.out.println("本机: ");
        String msg = input.nextLine();
        if ("exit".equalsIgnoreCase(msg)) {
            f = false;
        }
        byte[] buf = msg.getBytes();
        try {
            InetAddress addresses = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf, buf.length,addresses,port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receive() {
        //接收消息
        System.out.println("等待消息：");
        try {
            socket.receive(datagramPacket);
            String str = new String(bytes, 0, datagramPacket.getLength());
            if ("exit".equalsIgnoreCase(str)) {
                f = false;
            }
            // 3.拆包(把数据从数据包解析出来)
            InetAddress address = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            System.out.println("来自于" + address + "-->" + "port" + port + "的消息: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (socket != null) {
            socket.close();
        }
    }
}
