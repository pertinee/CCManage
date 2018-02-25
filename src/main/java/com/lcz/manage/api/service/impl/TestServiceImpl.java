package com.lcz.manage.api.service.impl;

import com.lcz.manage.api.bean.TestBean;
import com.lcz.manage.api.dao.TestDao;
import com.lcz.manage.api.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/2/9.
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    /**
     * 查询对象
     * @param id
     * @return
     */
    @Override
    public TestBean queryObject(String id) {
        return testDao.queryObject(id);
    }

    /**
     * 查询对象集合
     * @param map
     * @return
     */
    @Override
    public List<TestBean> queryList(Map<String, Object> map) {
        return testDao.queryList(map);
    }

    /**
     * 保存对象
     * @param testBean
     */
    @Override
    public void save(TestBean testBean) {
        testDao.save(testBean);
    }

    /**
     * 更新对象
     * @param testBean
     * @return
     */
    @Override
    public int update(TestBean testBean) {
        return testDao.update(testBean);
    }

    /**
     * 删除对象
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        return testDao.delete(id);
    }

    /**
     * 测试事务是否回滚（该方法会抛出空指针异常）
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTrans(String id) {
        testDao.delete(id);

        //异常
        Object object = null;
        object.toString();

    }
}
