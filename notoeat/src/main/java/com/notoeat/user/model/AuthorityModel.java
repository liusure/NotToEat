package com.notoeat.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notoeat.core.model.Model;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by l on 2017/4/23.
 */
@Entity
@Table(name = "authorities")
public class AuthorityModel extends Model implements GrantedAuthority {
    public static Integer STATUS_ENABLE=1;
    private Integer id;
    private Set<UserModel> users;
    private Set<GroupModel> groups;
    private String authority;
    private String description;
    private Integer status;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToMany(mappedBy = "authorities",fetch = FetchType.EAGER)
    @JsonIgnore
    public Set<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupModel> groups) {
        this.groups = groups;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
