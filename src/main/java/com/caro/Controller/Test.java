package com.caro.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mtime on 2017/8/14.
 */
@Controller
public class Test {

    @ResponseBody
    @RequestMapping(value = "home" ,method = RequestMethod.GET)
    public String getString(){
        System.out.print( "hello 世界");
        return "hello 世界";
    }

    public  static void main(String[] args){
        SpringApplication.run(Test.class,args);
    }
}
