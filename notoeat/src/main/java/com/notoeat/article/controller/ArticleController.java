package com.notoeat.article.controller;

import com.notoeat.article.model.ArticleModel;
import com.notoeat.article.service.ArticleService;
import com.notoeat.core.model.JsonResult;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.talking.service.TalkingService;
import com.notoeat.user.model.UserModel;
import com.notoeat.utils.PageResults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService<ArticleModel, Integer> articleService;
    @Resource
    private TalkingService<TalkingModel, Integer> talkingService;
    @RequestMapping("/saveArticle")
    @ResponseBody
    public JsonResult<ArticleModel> saveArticle(ArticleModel articleModel){
        Date createTime = new Date();
        JsonResult<ArticleModel> jsonResult = new JsonResult<ArticleModel>();
        try {
            articleModel.setStatus(ArticleModel.STATUS_ENABLED);
            articleModel.setCreateTime(createTime);
            articleModel.setType(ArticleModel.TYPE_PLAIN);
            UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            articleModel.setCreator(userModel);
            TalkingModel talking = new TalkingModel();
            talking.setStatus(TalkingModel.STATUS_ENABLED);
            talkingService.save(talking);
            articleModel.setTalking(talking);
            articleService.save(articleModel);
            jsonResult.setResult(articleModel);
            jsonResult.setSuccess();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/getArticles")
    @ResponseBody
    public PageResults<ArticleModel> getArticles(PageResults page,Integer type){
        Date createTime = new Date();
        PageResults<ArticleModel> pageResults = null;
        try {
            String hql = "FROM ArticleModel a WHERE a.status = ? and a.type = ? ORDER BY a.id DESC";
            pageResults = articleService.findPageByFetchedHql(hql,null,page.getCurrentPage(),page.getPageSize(),ArticleModel.STATUS_ENABLED,type);
        } catch (Exception e){
            e.printStackTrace();
        }
        return pageResults;
    }
    @RequestMapping("/getArticle")
    @ResponseBody
    public JsonResult<ArticleModel> getArticle(Integer id){
        Date createTime = new Date();
        JsonResult<ArticleModel> jsonResult = new JsonResult<ArticleModel>();
        try {
            String hql = "FROM ArticleModel a WHERE a.status = ? and a.id = ?";
            ArticleModel articleModel = articleService.getByHQL(hql,ArticleModel.STATUS_ENABLED,id);
            UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            articleModel.getVisited().add(userModel);
            articleService.update(articleModel);
            jsonResult.setResult(articleModel);
            jsonResult.setSuccess();
        } catch (Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
}
