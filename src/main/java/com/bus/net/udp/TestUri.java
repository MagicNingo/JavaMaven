package com.bus.net.udp;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class TestUri {
    /*
        URI: Uniform Resource Identifier，统一资源标识符;
        URL: Uniform Resource Locator，统一资源定位符;
        URN: Uniform Resource Name，统一资源名称。

     */
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream input = url.openStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        BufferedWriter writer = new BufferedWriter(new FileWriter("d:/baidu.html"));

        String str = null;
        while ((str = reader.readLine()) != null) {
            // 打印到控制台
            System.out.println(str);
            //输出到文件
            writer.write(str);
        }
        writer.close();
        reader.close();

    }
}
