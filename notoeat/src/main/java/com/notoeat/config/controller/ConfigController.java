package com.notoeat.config.controller;

import com.notoeat.config.model.ConfigModel;
import com.notoeat.config.service.ConfigService;
import com.notoeat.core.model.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/config")
public class ConfigController {
    @Resource
    private ConfigService<ConfigModel,Integer> configService;
    @RequestMapping("/getVisitorNum")
    @ResponseBody
    public JsonResult<String> getVisitorNum(){
        JsonResult<String> jsonResult = new JsonResult<>();
        try {
            ConfigModel configModel = configService.getConfigByName("visitorNum");
            if(configModel.getValue()!=null){
                jsonResult.setSuccess();
                jsonResult.setMsg(configModel.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
