package com.notoeat.media.controller;

import com.notoeat.core.model.JsonResult;
import com.notoeat.media.model.MediaModel;
import com.notoeat.media.service.MediaService;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.talking.service.TalkingService;
import com.notoeat.user.model.UserModel;
import com.notoeat.utils.ImageUtil;
import com.notoeat.utils.VideoUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by l on 2017/4/20.
 */
@Controller
@RequestMapping("/media")
public class MediaController {
    @Resource
    private MediaService<MediaModel, Integer> mediaService;
    @Resource
    private TalkingService<TalkingModel, Integer> talkingService;
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResult<MediaModel> fileUpload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        Date createTime = new Date();
        JsonResult<MediaModel> jsonResult = new JsonResult<MediaModel>();
        try {
            String localPath = file.getContentType().split("/")[0];
            File path = new File(request.getSession().getServletContext().getRealPath("/")+"/file/src/"+localPath);
            if(!path.exists()){
                path.mkdirs();
            }
            String localFileName = "/"+createTime.getTime()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File newFile = new File(path.getAbsolutePath()+localFileName);
            file.transferTo(newFile);
            MediaModel mediaModel = new MediaModel();
            String type = (String) request.getAttribute("type");
            if(type!=null&&!"".equals(type)){
                mediaModel.setType(Integer.parseInt(type));
            }
            mediaModel.setCreateTime(createTime);
            mediaModel.setFileName(file.getOriginalFilename());
            mediaModel.setFileType(file.getContentType());
            mediaModel.setStatus(MediaModel.STATUS_ENABLED);
            mediaModel.setFilePath("/file/src/"+localPath+localFileName);
            UserModel userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            mediaModel.setCreator(userModel);
            TalkingModel talking = new TalkingModel();
            talking.setStatus(TalkingModel.STATUS_ENABLED);
            talkingService.save(talking);
            mediaModel.setTalking(talking);
            mediaService.save(mediaModel);
            /*文件上传完成，按类型处理，图片生成缩略图、视频生成GIF图*/
            if(mediaModel.getFileType().contains("image")){
                File thumbnailPath = new File(request.getSession().getServletContext().getRealPath("/")+"/file/thumbnail/"+localPath);
                if(!thumbnailPath.exists()){
                    thumbnailPath.mkdirs();
                }
                File newThumbnailFile = new File(thumbnailPath.getAbsolutePath()+localFileName);
                try {
                    ImageUtil.getInstance().makeThumbnail(newFile,newThumbnailFile,200,null);
                } catch (Exception e) {
                    e.printStackTrace();
                    jsonResult.setMsg("生成缩略图失败");
                }
            } else if(mediaModel.getFileType().contains("video")){
                File thumbnailPath = new File(request.getSession().getServletContext().getRealPath("/")+"/file/thumbnail/"+localPath);
                if(!thumbnailPath.exists()){
                    thumbnailPath.mkdirs();
                }
                File newThumbnailFile = new File(thumbnailPath.getAbsolutePath()+"/"+createTime.getTime()+".gif");
                try {
                    VideoUtil.getInstance().makeThumbnail(newFile,newThumbnailFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    jsonResult.setMsg("生成缩略图失败");
                }
            }
            jsonResult.setResult(mediaModel);
            jsonResult.setSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    @RequestMapping("/uploadArticleImage")
    @ResponseBody
    public String uploadArticleImage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        request.setAttribute("type","3");
        JsonResult<MediaModel> jsonResult = fileUpload(file,request);
        if(jsonResult.getSuccess()){
            return jsonResult.getResult().getFilePath();
        }
        return "error|图片上传失败";
    }
    @RequestMapping("/getFiles")
    @ResponseBody
    public JsonResult<List<MediaModel>> getFiles(String type,String scope){
        JsonResult<List<MediaModel>> jsonResult = new JsonResult();
        try {
            String hql = "FROM MediaModel m where m.status = ? and m.type=?";
            UserModel userModel = null;
            String[] types = null;
            if("personal".equals(scope)){
                hql+=" AND m.creator = ?";
                userModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            }
            if(type!=null&&!"".equals(type)){
                hql+=" and m.fileType in(:arr_3)";
                types = MediaModel.TYPES.get(type);
            }

            List<MediaModel> mediaModels = mediaService.getListByHQL(hql,MediaModel.STATUS_ENABLED,MediaModel.TYPE_FILE,userModel,types);
            jsonResult.setResult(mediaModels);
            jsonResult.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonResult;
    }
    @RequestMapping("/getFile")
    @ResponseBody
    public JsonResult<MediaModel> getFile(Integer id){
        JsonResult<MediaModel> jsonResult = new JsonResult<>();
        try {
            MediaModel mediaModel = mediaService.getByHQL("FROM MediaModel m where m.status = ? and id = ?",MediaModel.STATUS_ENABLED,id);
            if(mediaModel!=null){
                jsonResult.setResult(mediaModel);
                jsonResult.setSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jsonResult;
    }
}
