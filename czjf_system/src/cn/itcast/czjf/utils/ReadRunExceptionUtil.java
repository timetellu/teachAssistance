package cn.itcast.czjf.utils;
import java.io.*;
public class ReadRunExceptionUtil {
    public static String  ReadRunException(int totalLines,File file) {
        // 指定开始读取的行号
        int line = 41;
        String result = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try{
        br = new BufferedReader(new FileReader(file));
            for(int i=1;i<line;i++){
                br.readLine();
            }
            while((result=br.readLine())!=null){
                sb.append(result+"\r\n");
                line++;
                if (line == totalLines){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
        return result;

    }

    //获取文件的行数
    public static int getTotalLines(File file) {
        BufferedReader br = null;
        LineNumberReader reader = null;
        int lines = 0;
        try{
            br = new BufferedReader(new FileReader(file));
            reader = new LineNumberReader(br);
            String s = reader.readLine();
            while (s != null) {
                lines++;
                s = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(br != null){
                    br.close();
                }
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}