package com.notoeat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by l on 2017/4/24.
 */
@Controller
public class WebController {
    @RequestMapping("/go")
    public String go(String url){
        return url;
    }
}
