package com.lcz.manage.util;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.sys.bean.SysDictInfoBean;
import com.lcz.manage.util.share.DictShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 数据字典工具类
 *
 * @author luchunzhou
 */
@Component
public class DictUtils {

    @Autowired
    private DictShare testConfig;

    private static DictShare dictShare;

    @PostConstruct
    public void init() {
        dictShare = testConfig;
    }

    public DictUtils(DictShare share) {
        dictShare = share;
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
