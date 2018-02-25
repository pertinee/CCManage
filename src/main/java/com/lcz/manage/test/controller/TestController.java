package com.lcz.manage.test.controller;

import com.lcz.manage.api.bean.TestBean;
import com.lcz.manage.api.service.TestService;
import com.lcz.manage.util.R;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/2/9.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    /**
     * 查询对象
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public R queryObject(String id) {
        TestBean bean = testService.queryObject(id);
        return R.ok().put("testBean", bean);
    }

    /**
     * 查询集合
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public R queryList(Map<String, Object> map) {
        List<TestBean> beanList = testService.queryList(map);
        return R.ok().put("testBeanList", beanList);
    }

    /**
     * 保存对象
     *
     * @param testBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public R save(@RequestBody TestBean testBean) {
        testService.save(testBean);
        return R.ok();
    }

    /**
     * 修改对象
     *
     * @param testBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R update(@RequestBody TestBean testBean) {
        testService.update(testBean);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public R delete(String id) {
        testService.delete(id);
        return R.ok();
    }

    /**
     * 测试事务1（控制层不加@Transactional）
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/tx")
    public R testTx(String id) {
        testService.testTrans(id);
        return R.ok();
    }

    /**
     * 测试事务2
     *
     * @param id
     * @return
     */
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/tx2")
    public R testTx2(String id) {
        testService.testTrans(id);
        return R.ok();
    }
}
