package com.lcz.manage.api.dao;

import com.lcz.manage.api.bean.TestBean;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/2/9.
 */
public interface TestDao {

    /**
     * 查询对象
     * @param id
     * @return
     */
    TestBean queryObject(String id);

    /**
     * 查询对象集合
     * @param map
     * @return
     */
    List<TestBean> queryList(Map<String, Object> map);

    /**
     * 保存对象
     * @param testBean
     */
    void save(TestBean testBean);

    /**
     * 更新对象
     * @param testBean
     * @return
     */
    int update(TestBean testBean);

    /**
     * 删除测试bean
     * @param id
     * @return
     */
    int delete(String id);
}
