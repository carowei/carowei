package util;

import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProperUtils {
    private static Logger log = LoggerManager.getLogger(ProperUtils.class);
    //系统用户路径
    private final  static String USER_DIR = System.getProperty("user.dir");

    // tomcat_home
    private final static String TOMCAT_HOME = USER_DIR.substring(0, USER_DIR
            .lastIndexOf(File.separator));

    //工程路径
    private  final static String PROJECT_PATH =  ApplicationUtils.getInstance().getRootPath().replaceAll("%20","");

    // 工程类路径
    public final static String CLASSPATH = PROJECT_PATH + "target"
            + "/classes";


    // 获取某资源文件 某属性对应 键值
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            // properties.load(PropertyUtils.class.getResourceAsStream(fileName));
            // log.info("classpath="+classpath);
            fis = new FileInputStream(CLASSPATH +"/etc/"+ fileName);
            properties.load(fis);

            return (String) properties.get(key);

        } catch (FileNotFoundException e) {
            log.info("资源文件" + fileName + "不存在");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("读取资源文件" + fileName + "发生IO异常");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static void main(String[] args){
        log.info("USER_DIR {} ",USER_DIR);
        log.info("TOMCAT_HOME  {}",TOMCAT_HOME);

        log.info("PROJECT_HOME {},CLASSPATH {} ",PROJECT_PATH,  CLASSPATH);
    }





    // 获取ip
    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0)
                || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0)
                || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0)
                || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
