package com.notoeat.talking.controller;

import com.notoeat.core.model.JsonResult;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.talking.service.TalkingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/talking")
public class TalkingController {
    @Resource
    private TalkingService<TalkingModel, Integer> talkingService;

    @RequestMapping("/getTalking")
    @ResponseBody
    public JsonResult<TalkingModel> getTalking(Integer id){
        Date createTime = new Date();
        JsonResult<TalkingModel> jsonResult = new JsonResult<TalkingModel>();
        try {
            TalkingModel talkingModel = talkingService.getByHQL("FROM TalkingModel t WHERE t.id = ?",id);
            jsonResult.setResult(talkingModel);
            jsonResult.setSuccess();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
}
