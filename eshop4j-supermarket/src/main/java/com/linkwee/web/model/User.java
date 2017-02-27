package com.linkwee.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User implements Serializable{
    /**
	 * 序列化
	 */
	private static final long serialVersionUID = 2958662941039937962L;

	private Integer id;

	@NotNull
    private String username;

	@NotNull
    private String password;

    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    private Date createTime;

    private String description;

    public User(){
    	
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", state=" + state + ", createTime=" + createTime + "]";
    }
}