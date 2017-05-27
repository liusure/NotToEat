package com.notoeat.user.controller;

import com.notoeat.article.model.ArticleModel;
import com.notoeat.article.service.ArticleService;
import com.notoeat.core.model.JsonResult;
import com.notoeat.media.model.MediaModel;
import com.notoeat.media.service.MediaService;
import com.notoeat.user.model.AuthorityModel;
import com.notoeat.user.model.UserModel;
import com.notoeat.user.service.AuthorityService;
import com.notoeat.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService<UserModel,Integer> userService;
    @Resource
    private MediaService<MediaModel,Integer> mediaService;
    @Resource
    private AuthorityService<AuthorityModel,Integer> authorityService;
    @Resource
    private ArticleService<ArticleModel,Integer> articleService;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private ServletContext servletContext;

    @RequestMapping("/checkDuplicateUser")
    @ResponseBody
    public JsonResult<UserModel> checkDuplicateUser(String userName){
        JsonResult<UserModel> jsonResult = new JsonResult<UserModel>();
        try {
            UserModel duplicateUser = userService.getByHQL("FROM UserModel u WHERE u.userName = ?",userName);
            if(duplicateUser==null){
                jsonResult.setSuccess();
            }else{
                jsonResult.setMsg("此用户名已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/register")
    @ResponseBody
    public JsonResult<UserModel> register(UserModel user, HttpServletRequest httpServletRequest){
        JsonResult<UserModel> jsonResult = new JsonResult<UserModel>();
        try {
            if(this.checkDuplicateUser(user.getUserName()).getSuccess()){
                AuthorityModel authority = authorityService.getByHQL("FROM AuthorityModel a WHERE a.authority = ?","ROLE_USER");
                user.getAuthorities().add(authority);
                String passWord = user.getPassWord();
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
                user.setStatus(UserModel.STATUS_ENABLED);
                user.setAlias(user.getUserName());
                user.setCreateTime(new Date());
                userService.save(user);
                jsonResult.setSuccess();
                jsonResult.setResult(user);
                jsonResult.setMsg("/index");
                httpServletRequest.getSession();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), passWord);
                token.setDetails(new WebAuthenticationDetails(httpServletRequest));
                Authentication authenticatedUser = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                httpServletRequest.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            }else{
                jsonResult.setMsg("此用户名已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/getUser")
    @ResponseBody
    public JsonResult<UserModel> getUser(){
        JsonResult jsonResult = new JsonResult();
        try {
            UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List files = mediaService.getListByHQL("FROM MediaModel m WHERE m.creator = ? and m.type = ? and m.status=?",userModel,MediaModel.TYPE_FILE, MediaModel.STATUS_ENABLED);
            List articles = articleService.getListByHQL("FROM ArticleModel a WHERE a.creator = ? and a.status = ?",userModel,ArticleModel.STATUS_ENABLED);
            if(userModel!=null){
                jsonResult.setResult(userModel);
                jsonResult.getParams().put("fileCount",files.size());
                jsonResult.getParams().put("articleCount",articles.size());
                jsonResult.setSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/uploadFace")
    @ResponseBody
    public JsonResult<MediaModel> uploadFace(@RequestBody String data){
        JsonResult<MediaModel> jsonResult = new JsonResult<MediaModel>();
        FileOutputStream fileOutputStream = null;
        UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Date createTime = new Date();
            BASE64Decoder decoder = new BASE64Decoder();
            String prefix = "data:image/png;base64,";
            if(data.contains(prefix)){
                data = data.substring(data.lastIndexOf(prefix)+prefix.length());
            }
            byte[] bdata = decoder.decodeBuffer(data);
            if(bdata!=null && bdata.length!=0){
                String localPath = "/file/face";
                File path = new File(servletContext.getRealPath("/")+localPath);
                if(!path.exists()){
                    path.mkdirs();
                }
                String localFileName = "/"+createTime.getTime()+".png";
                File newFile = new File(path.getAbsolutePath()+localFileName);
                fileOutputStream = new FileOutputStream(newFile);
                fileOutputStream.write(bdata);
                fileOutputStream.flush();
                MediaModel mediaModel = new MediaModel();
                mediaModel.setFileName(createTime.getTime()+"");
                mediaModel.setFileType("image/png");
                mediaModel.setFilePath(localPath+localFileName);
                mediaModel.setCreateTime(createTime);
                mediaModel.setType(MediaModel.TYPE_FACE);
                mediaModel.setCreator(userModel);
                mediaModel.setStatus(MediaModel.STATUS_ENABLED);
                mediaService.save(mediaModel);
                userModel.setFace(mediaModel);
                userService.update(userModel);
                jsonResult.setResult(mediaModel);
                jsonResult.setSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResult;
    }
}
