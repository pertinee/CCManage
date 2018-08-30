package com.lcz.manage.sys.bean;

import com.lcz.manage.util.validator.group.AddGroup;
import com.lcz.manage.util.validator.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 用户表
 *
 * @author luchunzhou
 */
@Data
public class SysUserBean implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private transient String password;

    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private String status;
    private String statusCn;

    /**
     * 角色ID列表
     */
    private List<String> roleIdList;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 部门ID
     */
    @NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

}