package com.notoeat.talking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notoeat.core.model.Model;
import com.notoeat.user.model.UserModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by l on 2017/4/22.
 */
@Entity
@Table(name = "comments")
public class CommentModel extends Model {
    public static Integer STATUS_ENABLED = 1;
    private Integer id;
    private String content;
    private Date createTime;
    private Integer status;
    private UserModel creator;
    private TalkingModel talking;
    private CommentModel parent;
    private Set<CommentModel> child;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @ManyToOne
    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public TalkingModel getTalking() {
        return talking;
    }

    public void setTalking(TalkingModel talking) {
        this.talking = talking;
    }

    @ManyToOne
    @JsonIgnore
    public CommentModel getParent() {
        return parent;
    }

    public void setParent(CommentModel parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    public Set<CommentModel> getChild() {
        return child;
    }

    public void setChild(Set<CommentModel> child) {
        this.child = child;
    }
}
