package com.zmh.util.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * java run shell command
 *
 * @author zengminghua
 */
public class ExecShellCmd {

    /**
     * init sl4j
     */
    private static Logger logger = LoggerFactory.getLogger(ExecShellCmd.class);

    public static void main(String[] args) {

        //        String shellCmd = "uptime";
        //        execShell(shellCmd);

//        String shellUrl = "uptime";
        String shellUrl = "/Users/zengminghua/Workspaces/zengminghua/github/test-util/src/com/zmh/util/test/data/testShell.sh";
        callShell(shellUrl);
    }

    public static void execShell(String shellString) {

        Process process = null;
        List<String> processList = new ArrayList<String>();
        try {
            process = Runtime.getRuntime().exec(shellString);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            logger.error("捕获到IO异常！错误信息：", e);
        }

        for (String line : processList) {
            logger.info("Shell命令执行结果：{}", line);
        }


        /*try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.error("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
            logger.error("call shell failed. " + e);
        }*/
    }

    public static void callShell(String shellString) {
        try {

            String[] cmd = { "/bin/bash", "-c", shellString + " 我是参数1" +" 我是参数2"};

            Process process = Runtime.getRuntime().exec(cmd);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.error("call shell failed. error code is :" + exitValue);
            }
        } catch (Throwable e) {
            logger.error("call shell failed. " + e);
        }
    }
}
