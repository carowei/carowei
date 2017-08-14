package util;


import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by carowei on 2017/8/10.
 */
public class PKUtils {

    private static String[] ls = new String[3000];
    private static int li = 0;
    private static Logger log = LoggerManager.getLogger(PKUtils.class);
    private PKUtils(){

    }

 private static synchronized  String getPK(){
        String numberic = initPK();
     for (int i = 0; i < 3000; i++) {
         String lt = ls[i];
         if (lt == numberic) {
             numberic = getPK();
             break;
         }
     }
     ls[li] = numberic;
     li++;
     if (li == 3000) {
         li = 0;
     }


        return numberic;
 }

    private static String initPK(){
        //截取当前日期到秒 12位
        String date =  (new SimpleDateFormat("yyyyMMddHHss").format(new Date())).trim();
        //获取随机数的8位值
       String  random = String.valueOf(Math.random()).substring(2,8).trim();
       //返回拼接

        return new StringBuffer( date + random).toString();
    }


    public static void main(String[] args){

        System.out.println(PKUtils.getPK());

    }
}
