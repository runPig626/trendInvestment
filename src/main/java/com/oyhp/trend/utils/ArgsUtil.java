package com.oyhp.trend.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
public class ArgsUtil {

    /**
     * 用户是否带端口参数启动
     * @param args
     * @param port
     * @return
     */
    public static int checkArgsPort(String[] args, int port){
        if(null!=args && 0!=args.length) {
            for (String arg : args) {
                if(arg.startsWith("port=")) {
                    String strPort= StrUtil.subAfter(arg, "port=", true);
                    if(NumberUtil.isNumber(strPort)) {
                        port = Convert.toInt(strPort);
                    }
                }
            }
        }
        return port;
    }

    /**
     * 用户输入端口启动
     * @param port
     * @param defaultPort
     */
    public static Future<Integer> startApplicationWithInputPort(int port, int defaultPort){
        Future<Integer> future = ThreadUtil.execAsync(() ->{
            int p = 0;
            System.out.printf("请于5秒钟内输入端口号, 推荐  %d ,超过5秒将默认使用 %d %n",defaultPort,defaultPort);
            Scanner scanner = new Scanner(System.in);
            while(true) {
                String strPort = scanner.nextLine();
                if(!NumberUtil.isInteger(strPort)) {
                    System.err.println("只能是数字");
                    continue;
                }
                else {
                    p = Convert.toInt(strPort);
                    scanner.close();
                    break;
                }
            }
            return p;
        });
        return future;
    }

}
