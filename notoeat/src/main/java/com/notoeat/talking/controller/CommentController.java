package com.notoeat.talking.controller;

import com.notoeat.core.model.JsonResult;
import com.notoeat.talking.model.CommentModel;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.talking.service.CommentService;
import com.notoeat.talking.service.TalkingService;
import com.notoeat.user.model.UserModel;
import com.notoeat.utils.PageResults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentService<CommentModel, Integer> commentsService;
    @Resource
    private TalkingService<TalkingModel, Integer> talkingService;

    @RequestMapping("/saveComment")
    @ResponseBody
    public JsonResult<CommentModel> saveComment(@RequestBody CommentModel commentsModel){
        Date createTime = new Date();
        JsonResult<CommentModel> jsonResult = new JsonResult<CommentModel>();
        try {
            commentsModel.setCreateTime(createTime);
            UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            commentsModel.setCreator(userModel);
            commentsModel.setStatus(CommentModel.STATUS_ENABLED);
            commentsService.save(commentsModel);
            jsonResult.setSuccess();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/getCommentPages")
    @ResponseBody
    public PageResults<CommentModel> getCommentPages(PageResults page,Integer talkingId){
        PageResults<CommentModel> pageResults = new PageResults<CommentModel>();
        try {
            TalkingModel talkingModel = talkingService.getByHQL("FROM TalkingModel t where t.id = ? and t.status = ?",talkingId,TalkingModel.STATUS_ENABLED);
            if(talkingModel!=null){
                pageResults = commentsService.findPageByFetchedHql("FROM CommentModel c WHERE c.status = ? and c.talking = ?",null,page.getCurrentPage(),page.getPageSize(),CommentModel.STATUS_ENABLED,talkingModel);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return pageResults;
    }
}
