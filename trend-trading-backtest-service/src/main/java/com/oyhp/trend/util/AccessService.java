package com.oyhp.trend.util;
 
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;
  
/**
 * @author OYHP
 */
public class AccessService {
  
    public static void main(String[] args) {
          
        while(true) {
            ThreadUtil.sleep(1000);
            try {
                String html= HttpUtil.get("http://127.0.0.1:8051/simulate/000300");
                System.out.println("html length:" + html.length());
            }
            catch(Exception e) {
                System.err.println(e.getMessage());
            }
  
        }
          
    }
}