package com.notoeat.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notoeat.core.model.Model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by l on 2017/4/23.
 */
@Entity
@Table(name = "groups")
public class GroupModel extends Model {

    private Integer id;
    private String groupName;
    private Set<AuthorityModel> authorities;
    private Set<UserModel> users;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<AuthorityModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityModel> authorities) {
        this.authorities = authorities;
    }
}
