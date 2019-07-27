package com.shgx.aop.controller;

import com.shgx.aop.annotation.MyLog;
import org.springframework.web.bind.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */
@RestController
public class TestController {

    @RequestMapping(path = "/test/{info}", method = RequestMethod.GET)
    @ResponseBody
    @MyLog(module="controller", method="TestController")
    public String test(@PathVariable("info") String info){
        return info;
    }
}
