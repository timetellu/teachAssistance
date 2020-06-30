package cn.timetell.stringCompiler;

import java.util.concurrent.Callable;


/**
 * CustomCallable 调用编译并运行，设置超时时间
 */

public class CustomCallable implements Callable<RunInfo> {
    private String sourceCode;

    public CustomCallable(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    //方案2
    @SuppressWarnings("deprecation")
	@Override
    public RunInfo call() throws InterruptedException {
        RunInfo runInfo = new RunInfo();

        Thread t1 = new Thread(() -> realCall(runInfo));
        t1.start();
        
        t1.join(5000); //timeout设置为5秒
       
        //不管有没有正常执行完成，强制停止t1
        t1.stop();
        return runInfo;
    }
    

    private void realCall(RunInfo runInfo) {
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(sourceCode);
        if (compiler.compiler()) {
            runInfo.setCompilerSuccess(true);
            try {
                compiler.runMainMethod();
                runInfo.setRunSuccess(true);
                runInfo.setRunTakeTime(compiler.getRunTakeTime());
                runInfo.setRunMessage(compiler.getRunResult()); //获取运行时候的输出内容
            } catch (Exception e) {
                e.printStackTrace();
                runInfo.setRunSuccess(false);
                runInfo.setRunMessage(e.getMessage());
            }
        } else {
            //编译失败
            runInfo.setCompilerSuccess(false);
        }
        runInfo.setCompilerTakeTime(compiler.getCompilerTakeTime());
        runInfo.setCompilerMessage(compiler.getCompilerMessage());
        runInfo.setTimeOut(false); //走到这一步代表没有超时
    }
    
}

//方案1
//@Override
//public RunInfo call() throws InvocationTargetException {
//  System.out.println("开始执行call" + LocalTime.now());
//  RunInfo runInfo = new RunInfo();
//  CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(sourceCode);
//  if (compiler.compiler()) {
//      runInfo.setCompilerSuccess(true);
//      try {
//          compiler.runMainMethod();
//          runInfo.setRunSuccess(true);
//          runInfo.setRunTakeTime(compiler.getRunTakeTime());
//          runInfo.setRunMessage(compiler.getRunResult()); //获取运行的时候输出内容
//      } catch (Exception e) {
//          e.printStackTrace();
//          runInfo.setRunSuccess(false);
//          runInfo.setRunMessage(e.getMessage());
//      }
//  } else {
//      //编译失败
//      runInfo.setCompilerSuccess(false);
//  }
//  runInfo.setCompilerTakeTime(compiler.getCompilerTakeTime());
//  runInfo.setCompilerMessage(compiler.getCompilerMessage());
//  System.out.println("call over" + LocalTime.now());
//  return runInfo;
//}