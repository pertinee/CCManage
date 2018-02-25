package com.lcz.manage.api.controller;

import com.lcz.manage.api.bean.TestBean;
import com.lcz.manage.api.service.TestService;
import com.lcz.manage.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/2/9.
 */
@RestController
@Api(value = "测试接口", description = "测试接口对象基本操作")
@RequestMapping("/api")
public class ApiController {
    private Logger logger = Logger.getLogger(ApiController.class);

    @Autowired
    private TestService testService;

    /**
     * 根据id查询对象
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "通过id查询接口对象", httpMethod = "GET", response = R.class, notes = "通过id查询接口对象详情")
    public R queryObject(@ApiParam(name = "id", required = true, value = "接口对象id") @PathVariable("id") String id) {
        TestBean bean = testService.queryObject(id);
        return R.ok().put("testBean", bean);
    }

    /**
     * 查询集合
     *
     * @param map
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询集合", httpMethod = "GET", response = R.class)
    public R queryList(@ApiParam(name = "map", value = "map") Map<String, Object> map) {
        List<TestBean> beanList = testService.queryList(map);
        return R.ok().put("testBeanList", beanList);
    }

    /**
     * 保存对象
     *
     * @param testBean
     * @return
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "保存对象", httpMethod = "POST")
    public R save(@ApiParam(name = "TestBean", required = true, value = "接口对象TestBean") @RequestBody TestBean testBean) {
        testService.save(testBean);
        return R.ok();
    }

    /**
     * 修改对象
     *
     * @param testBean
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改对象", httpMethod = "POST")
    public R update(@ApiParam(name = "TestBean", required = true, value = "接口对象TestBean") @RequestBody TestBean testBean) {
        testService.update(testBean);
        return R.ok();
    }

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除对象", httpMethod = "GET", response = R.class, notes = "通过id删除对象详情")
    public R delete(@ApiParam(name = "id", required = true, value = "接口对象id") @PathVariable("id") String id) {
        testService.delete(id);
        return R.ok();
    }

    /**
     * 测试事务1（控制层不加@Transactional）
     *
     * @param id
     * @return
     */
    @GetMapping("/tx/{id}")
    @ApiOperation(value = "测试事务", httpMethod = "GET", response = R.class, notes = "测试事务：控制层不加@Transactional")
    public R testTx(@ApiParam(name = "id", required = true, value = "接口对象id") @PathVariable("id") String id) {
        testService.testTrans(id);
        return R.ok();
    }

    /**
     * 测试事务2
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/tx2/{id}")
    @ApiOperation(value = "测试事务2", httpMethod = "GET", response = R.class, notes = "测试事务：控制层不加@Transactional")
    public R testTx2(@ApiParam(name = "id", required = true, value = "接口对象id") @PathVariable("id") String id) {
        testService.testTrans(id);
        return R.ok();
    }
}
