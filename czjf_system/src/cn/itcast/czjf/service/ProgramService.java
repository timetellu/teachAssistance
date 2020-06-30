package cn.itcast.czjf.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import cn.itcast.czjf.utils.ExceptionDetailUtil;
import cn.itcast.czjf.utils.ReadRunExceptionUtil;
import cn.timetell.stringCompiler.CompilerUtil;
import cn.timetell.stringCompiler.CustomStringJavaCompiler;
import cn.timetell.stringCompiler.RunInfo;


public class ProgramService implements Runnable {
	
	public String code;
    StringBuffer sb = new StringBuffer();
    
    public ProgramService() {}
	public ProgramService(String code) {
        this.code = code;
    }
	
	@Override
    public void run() {
    	RunInfo runInfo;
		try {
			runInfo = CompilerUtil.getRunInfo(code);
			if (runInfo.getCompilerSuccess()) {
			    sb.append("【编译成功】\n");
			    sb.append("编译消耗时间："+ runInfo.getCompilerTakeTime() + "ms\n");
			    sb.append("运行消耗时间：" + runInfo.getRunTakeTime()+ "ms\n");
			    sb.append("运行结果如下：\n"+runInfo.getRunMessage());
	//		    System.out.println(sb.toString());
			} else {
		        sb.append("【编译失败】\n");
		        sb.append("诊断信息：\n");
		        sb.append(runInfo.getCompilerMessage());
	//	        System.out.println(sb.toString());
				}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }
	
	//运行输入的代码
	public String runInputCode(String inputCode) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		ProgramService t_run = new ProgramService(inputCode);
		executor.submit(t_run);
		return sb.toString();
	}

	//运行上传的代码
	public String runUploadCode(String uploadCode) throws IllegalAccessException, InstantiationException {
		// 具体的实现代码
		CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(uploadCode);
        boolean res = compiler.compiler();
        StringBuffer sb_success = new StringBuffer();
        StringBuffer sb_fail = new StringBuffer();
        if (res) {
            sb_success.append("【编译成功】\n");
            sb_success.append("编译消耗时间："+ compiler.getCompilerTakeTime() + "ms\n");
            try {
                compiler.runMainMethod();
                sb_success.append("运行消耗时间：" + compiler.getRunTakeTime()+ "ms\n");
            } catch (Exception e) {
                sb_success.append("出现运行时异常\n");
                e.printStackTrace();
            }
            sb_success.append("运行结果如下：\n"+compiler.getRunResult());
//            System.out.println(sb_success.toString());
            return sb_success.toString();
        } else {
            sb_fail.append("【编译失败】\n");
            sb_fail.append("诊断信息：\n");
            sb_fail.append(compiler.getCompilerMessage());
//            System.out.println(sb_fail.toString());
            return sb_fail.toString();
        }
	}

	
}


