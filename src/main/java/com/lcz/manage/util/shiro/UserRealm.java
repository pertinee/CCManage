package com.lcz.manage.util.shiro;

import com.lcz.manage.sys.bean.SysUserBean;
import com.lcz.manage.sys.enums.ProfilesActive;
import com.lcz.manage.sys.enums.UserStatus;
import com.lcz.manage.sys.enums.YesOrNo;
import com.lcz.manage.sys.service.SysMenuService;
import com.lcz.manage.sys.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 * 
 * @author luchunzhou
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    @Value("${spring.profiles.active}")
    private String profilesActive;
    
    /**
     * 授权(验证权限时调用)
     * 提示：可以在controller上加上@RequiresPermissions("sys:user:list")注解
     */
    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserBean user = (SysUserBean)principals.getPrimaryPrincipal();
		String userId = user.getUserId();

		//用户权限列表
        Set<String> permsSet = sysMenuService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        
        //查询用户信息
        SysUserBean user = sysUserService.queryByUserName(username);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // STEP2：开发环境不需要密码验证
        if(!ProfilesActive.DEV.getCode().equals(profilesActive)){
            //密码错误
            if(!password.equals(user.getPassword())) {
                throw new IncorrectCredentialsException("账号或密码不正确");
            }
        }

        //账号锁定
        if(UserStatus.DISABLED.getCode().equals(user.getStatus())){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
	}

}