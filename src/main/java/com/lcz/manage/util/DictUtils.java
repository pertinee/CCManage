package com.lcz.manage.util;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.util.share.DictShare;

import java.util.List;

/**
 * 数据字典工具类
 *
 * @author luchunzhou
 */
public class DictUtils {

    private static DictShare dictShare;

    static {
        DictUtils.dictShare = (DictShare) SpringContextUtils.getBean("dictShare");
    }

    /**
     * 数据字典对象
     * @param dictId
     * @return
     */
    public static SysDictBean getDict(String dictId){
        return dictShare.getDict(dictId);
    }

    /**
     * 数据字典详情集合
     * @param dictId
     * @return
     */
    public static List<SysDictInfoBean> getDictInfoList(String dictId){
        return dictShare.getDictInfoList(dictId);
    }

    /**
     * 单个数据字典的转换
     * @param dictId
     * @param dictValue
     * @return
     */
    public static String getDictPrompt(String dictId, String dictValue){
        return dictShare.getDictPrompt(dictId,dictValue);
    }
}
