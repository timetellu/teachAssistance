package cn.itcast.czjf.utils;

import java.io.File;
import java.io.FilenameFilter;

/*
 * 文件过滤器：
 * 参考:https://blog.csdn.net/cjp0326/article/details/47274049
 */
public class FileNameFilterUtil implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("java");
    }

}

