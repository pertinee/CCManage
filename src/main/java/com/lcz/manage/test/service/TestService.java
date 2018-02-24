package com.lcz.manage.test.service;

import com.lcz.manage.test.bean.TestBean;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/2/9.
 */
public interface TestService {

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
     * 更新对象
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 测试事务是否回滚（该方法会抛出空指针异常）
     * @param id
     */
    void testTrans(String id);
}
