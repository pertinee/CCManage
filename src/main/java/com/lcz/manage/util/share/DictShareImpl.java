package com.lcz.manage.util.share;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.sys.dao.SysDictDao;
import com.lcz.manage.sys.dao.SysDictInfoDao;
import com.lcz.manage.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明: TODO (这里用一句话描述这个方法的作用)
 *
 * @author lcz <lucz25053@hsjry.com>
 * @version CFM-V1.0
 * @date 2018年09月14日16:04:01
 */
@Component
public class DictShareImpl extends DictShare {

    @Autowired
    private SysDictDao sysDictDao;
    @Autowired
    private SysDictInfoDao sysDictInfoDao;

    /**
     * 数据字典对象
     * @param dictId
     * @return
     */
    @Override
    public SysDictBean getDict(String dictId) {
        return this.sysDictDao.queryDict(dictId);
    }


    /**
     * 数据字典详情集合
     * @param dictId
     * @return
     */
    @Override
    public List<SysDictInfoBean> getDictInfoList(String dictId) {
        SysDictInfoBean sysDict = new SysDictInfoBean();
        sysDict.setId(dictId);
        return this.sysDictInfoDao.queryDictInfoList(sysDict);
    }


    /**
     * 单个数据字典的转换
     * @param dictId
     * @param value
     * @return
     */
    @Override
    public String getDictPrompt(String dictId, String value) {
        if(!StringUtils.isEmpty(dictId) && !StringUtils.isEmpty(value)) {
            SysDictInfoBean item = this.sysDictInfoDao.queryDictInfo(dictId, value);
            if(item != null) {
                return item.getDictPrompt();
            }
        }
        return null;
    }
}
