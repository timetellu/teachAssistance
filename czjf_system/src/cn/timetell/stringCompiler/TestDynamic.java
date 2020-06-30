package cn.timetell.stringCompiler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class TestDynamic {

        public static void main(String[] args) throws InterruptedException {
                String loop = "public class HelloWorld {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        while(true){\n" +
                        "            System.out.println(\"Hello World!\");\n" +
                        "        }\n" +
                        "       \n" +
                        "    }\n" +
                        "}";

                String sleep_loop = "public class HelloWorld {\n" +
                        "    public static void main(String[] args) {\n" +
                        "    try {\n" +
                        "            Thread.sleep(6000);\n" +
                        "        } catch (InterruptedException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "       System.out.println(\"Hello World!\");\n" +
                        "        while(true){\n" +
                        //"            System.out.println(\"Hello World!\");\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";

                String ok = "public class HelloWorld {\r\n" + 
                		"    public static void main(String[] args) {\r\n" + 
                		"        int i = 3 / 0;\r\n" + 
                		"        int[] arr = new int[2];\r\n" + 
                		"        arr[3] = 3;\r\n" + 
                		"        System.out.println(\"hello world2323\");\r\n" + 
                		"    }\r\n" + 
                		"}\r\n" + 
                		"";

                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
             
                //5.使用submit()方法提交任务给执行者
                TestRun t = new TestRun(ok);
                TestRun t1 = new TestRun(loop);
                TestRun t2 = new TestRun(sleep_loop);
                
				executor.submit(t2);
				
        }

}

class TestRun implements Runnable {
	public String code;
    StringBuffer sb = new StringBuffer();
    
    public TestRun() {}
	public TestRun(String code) {
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
			    System.out.println(sb.toString());
			} else {
		        sb.append("【编译失败】\n");
		        sb.append("诊断信息：\n");
		        sb.append(runInfo.getCompilerMessage());
		        System.out.println(sb.toString());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
        	
        }

}
