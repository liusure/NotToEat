package com.notoeat.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.notoeat.core.model.Model;
import com.notoeat.user.model.AuthorityModel;
import com.notoeat.user.model.UserModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by l on 2017/4/23.
 */
@Entity
@Table(name = "config")
@JsonIgnoreProperties(value={"users"})
public class ConfigModel extends Model {

    private Integer id;
    private String name;
    private String value;
    private Integer status;
    private String description;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
