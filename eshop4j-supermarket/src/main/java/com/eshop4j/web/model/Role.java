package com.eshop4j.web.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Role implements Serializable{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2703979603896541414L;

	private Integer id;

	@NotBlank(message ="不能为空")
    private String roleName;

	@NotBlank(message ="不能为空")
    private String roleSign;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign == null ? null : roleSign.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    @Override
    public String toString() {
        return "Role [id=" + id + ", roleName=" + roleName + ", roleSign=" + roleSign + ", description=" + description + "]";
    }
}