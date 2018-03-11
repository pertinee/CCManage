package com.lcz.manage.sys.dao;


import com.lcz.manage.sys.bean.SysLogBean;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogDao extends BaseDao<SysLogBean> {

    /**
     * 查询每日日志list
     * @param map
     * @return
     */
    List<SysLogBean> queryLogDaily();
	
}
