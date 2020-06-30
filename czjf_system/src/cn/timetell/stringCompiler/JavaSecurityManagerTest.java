package cn.timetell.stringCompiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FilePermission;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JavaSecurityManagerTest {
	public static void main(String[] args) {  
        setSecurityManagerForApp();  
        String str = "测试远程代码文件IO权限";
        BufferedWriter bw = null;
        BufferedReader br = null;
        //此filePath为服务器的realPath
        String filePath = "F:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\czjf_system\\upload\\testIO.txt";
        try {
            //文件写操作
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(str);
            bw.flush();

            //文件读操作
            //读取打印输出的内容，赋值给字符串
            br = new BufferedReader(new FileReader(filePath));
            char[] data=new char[1024*1024];
            int len=0;
            String result = null;
            while((len=br.read(data))!=-1) //读取文件并把它存入buf中，用num返回读到字符的个数，一直读到结尾
            {
                result = new String(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bw != null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }  
      
    public static void setSecurityManagerForApp(){  
        System.out.println(System.getSecurityManager());  
        SecurityManager safeManager = System.getSecurityManager();  
        safeManager.checkPermission(new FilePermission("F:\\eclipse\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\czjf_system\\upload\\testIO.txt","write"));  
        System.out.println("hah");  
    }  
}
