package com.lcz.manage.sys.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 角色表
 *
 * @author luchunzhou
 */
@Data
public class SysRoleBean implements Serializable {
    
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private String createUserId;

    private List<String> menuIdList;

    /**
     * 创建时间
     */
    private Date createDate;

}