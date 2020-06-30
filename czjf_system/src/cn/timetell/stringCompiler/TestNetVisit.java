package cn.timetell.stringCompiler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 * 验证【服务端安全性检测】
 * 测试 远程代码通过服务器访问网络的权限
 */

public class TestNetVisit {
    public static void main(String[] args) throws IOException {
        //访问网络：百度
        URL url = new URL("https://www.baidu.com/");
        URLConnection connection = url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) >= 0) {
            System.out.println(new String(bytes));
        }
    }
}
