package util;

import mtime.lark.util.log.Logger;
import mtime.lark.util.log.LoggerManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class DataBase {

    private static Properties envs;
    private static Logger log = LoggerManager.getLogger(DataBase.class);

    static{
        try {
            envs = new Properties();
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/etc";
            envs.load(new FileInputStream(new File(path, "mysql.properties")));
        } catch (Exception e) {
            log.info("文件加载异常 {} ",e.getStackTrace());
        }
    }

    private static String getValue(String key) {

        return envs.getProperty(key);

    }
    public static List<String> getKeys(){
        List<String> lists = new ArrayList<String>();
        Set<Object> keys = envs.keySet();
        for(Object o:keys){
            String k = (String)o;
            lists.add(k);
        }
        return lists;
    }
public   static  void  main(String[] args){

   log.info(DataBase.getValue("caro.mysql.address"));

    log.info( ProperUtils.getProperty("mysql.properties","caro.mysql.address"));


}
}
