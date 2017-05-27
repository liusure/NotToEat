package com.notoeat.media.model;

import com.notoeat.core.model.Model;
import com.notoeat.talking.model.Talkable;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.user.model.UserModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by l on 2017/4/22.
 */
@Entity
@Table(name = "medias")
public class MediaModel extends Model implements Talkable {
    public static Integer STATUS_ENABLED = 1;
    public static Integer TYPE_FILE = 1;
    public static Integer TYPE_FACE = 2;
    public static Integer TYPE_ARTICLE = 3;
    public static Map<String,String[]> TYPES;
    private Integer id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Integer type = TYPE_FILE;
    private Date createTime;
    private Integer status;
    private UserModel creator;
    private TalkingModel talking;
    static {
        TYPES = new HashMap<String,String[]>();
        TYPES.put("image",new String[]{"image/jpeg","image/gif","image/png"});
        TYPES.put("video",new String[]{"video/mpeg4","video/mp4","video/x-flv"});
        TYPES.put("audio",new String[]{"audio/mp3" });
        TYPES.put("text",new String[]{});
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }
    @Transient
    public String getFileTypeShort() { return fileType.split("/")[0]; }
    @Transient
    public String getFileContentType() { return filePath.substring(filePath.lastIndexOf(".")+1); }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }
    @Transient
    public String getThumbnailPath() {
        if("video".equals(getFileTypeShort())) {
            return filePath.substring(0,filePath.lastIndexOf(".")).replace("/src/","/thumbnail/")+".gif";
        }
        return filePath.replace("/src/","/thumbnail/");
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @ManyToOne
    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    @OneToOne
    public TalkingModel getTalking() {
        return talking;
    }

    public void setTalking(TalkingModel talking) {
        this.talking = talking;
    }
}
