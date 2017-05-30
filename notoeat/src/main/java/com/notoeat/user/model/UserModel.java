package com.notoeat.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notoeat.article.model.ArticleModel;
import com.notoeat.core.model.Model;
import com.notoeat.media.model.MediaModel;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by l on 2017/4/22.
 */
@Entity
@Table(name = "users")
public class UserModel extends Model implements UserDetails {
    public static Integer STATUS_ENABLED = 1;
    private Integer id;
    private String alias;
    private MediaModel face;
    private String userName;
    private String passWord;
    private Date createTime;
    private Integer status;
    private Set<AuthorityModel> authorities = new HashSet<AuthorityModel>();
    private Set<GroupModel> groups = new HashSet<GroupModel>();
    private Set<ArticleModel> articleHistory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true,length = 64)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonIgnore
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    @ManyToMany(fetch=FetchType.EAGER)
    public Set<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupModel> groups) {
        this.groups = groups;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @OneToOne
    @JsonIgnore
    public MediaModel getFace() {
        return face;
    }
    @Transient
    public String getFacePath() {
        return face==null?"":face.getFilePath();
    }
    public void setFace(MediaModel face) {
        this.face = face;
    }

    @Override
    @ManyToMany(fetch=FetchType.EAGER)
    public Set<AuthorityModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityModel> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany
    @JoinTable(name="visited_history",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name="article_id")})
    @JsonIgnore
    public Set<ArticleModel> getArticleHistory() {
        return articleHistory;
    }

    public void setArticleHistory(Set<ArticleModel> articleHistory) {
        this.articleHistory = articleHistory;
    }

    /* UserDetails */
    @Transient
    @Override
    @JsonIgnore
    public String getPassword() {
        return passWord;
    }

    @Transient
    @Override
    @JsonIgnore
    public String getUsername() {
        return userName;
    }

    /**
     * 用户账号是否过期
     * @return
     */
    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     * @return
     */
    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     * @return
     */
    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     * @return
     */
    @Transient
    @Override
    public boolean isEnabled() {
        return STATUS_ENABLED.equals(status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserModel other = (UserModel) obj;
        return Objects.equals(this.userName, other.userName);
    }
}
