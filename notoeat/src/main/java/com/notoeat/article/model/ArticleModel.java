package com.notoeat.article.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notoeat.core.model.Model;
import com.notoeat.talking.model.Talkable;
import com.notoeat.talking.model.TalkingModel;
import com.notoeat.user.model.UserModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by l on 2017/4/22.
 */
@Entity
@Table(name = "articles")
public class ArticleModel extends Model implements Talkable {
    public static Integer STATUS_ENABLED = 1;
    public static Integer TYPE_PLAIN = 1;
    private Integer id;
    private String name;
    private String content;
    private Integer type;
    private Date createTime;
    private Integer status;
    private UserModel creator;
    private TalkingModel talking;
    private Set<UserModel> visited;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @Override
    @OneToOne
    public TalkingModel getTalking() {
        return talking;
    }
    public void setTalking(TalkingModel talking) {
        this.talking = talking;
    }

    @ManyToMany
    @JoinTable(name="visited_history",joinColumns = {@JoinColumn(name = "article_id")},inverseJoinColumns = {@JoinColumn(name="user_id")})
    @JsonIgnore
    public Set<UserModel> getVisited() {
        return visited;
    }
    @Transient
    public Integer getVisitedCount() {
        if(visited!=null){
            return visited.size();
        }
        return 0;
    }
    public void setVisited(Set<UserModel> visited) {
        this.visited = visited;
    }
}
