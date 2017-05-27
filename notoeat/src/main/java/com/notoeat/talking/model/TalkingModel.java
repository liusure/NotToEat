package com.notoeat.talking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by l on 2017/5/7.
 */
@Entity
@Table(name = "talking")
public class TalkingModel {
    public static Integer STATUS_ENABLED = 1;
    private Integer id;
    private Integer status;
    private Set<CommentModel> comments;
    private Talkable talkable;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @OneToMany(mappedBy = "talking")
    @JsonIgnore
    public Set<CommentModel> getComments() {
        return comments;
    }
    @Transient
    public Integer getCommentsCount() {
        if(comments!=null){
            return comments.size();
        }
        return 0;
    }
    public void setComments(Set<CommentModel> comments) {
        this.comments = comments;
    }
    @OneToOne(mappedBy ="talking")
    @JsonIgnore
    public Talkable getTalkable() {
        return talkable;
    }

    public void setTalkable(Talkable talkable) {
        this.talkable = talkable;
    }
}
