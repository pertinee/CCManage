package com.lcz.manage.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcz.manage.test.bean.TestBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by luchunzhou on 2018/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBeanTests {

    @Autowired
    private WebApplicationContext context;

    //mock api 模拟http请求
    private MockMvc mockMvc;


    //初始化工作
    @Before
    public void setUp() {
        //独立安装测试
//        mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
        //集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /***
     * 测试根据id获取对象
     * @throws Exception
     */
    @Test
    public void testQueryObject() throws Exception {
        mockMvc.perform(get("/test/object?id=3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code", is(0)));
//                .andExpect(jsonPath("$.testBean", notNullValue()))
//                .andExpect(jsonPath("$.testBean.id", is(2)))
//                .andExpect(jsonPath("$.testBean.name", is("are you ok?")));
    }

    /***
     * 获取对象集合
     * @throws Exception
     */
    @Test
    public void testQueryList() throws Exception {
        mockMvc.perform(get("/test/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.testBeanList", notNullValue()));
    }

    /***
     * 测试保存对象
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        //构造添加的对象信息
        TestBean bean = new TestBean();
        String uuid = UUID.randomUUID().toString().replace("-","");
        bean.setId(uuid);
        bean.setName("mini虾_" + uuid);
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的用户参数
        mockMvc.perform(post("/test/save")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(bean)))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下：{"code":0}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code", is(0)));
    }

    /***
     * 测试更新对象
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        //构造要修改的对象信息
        TestBean bean = new TestBean();
        bean.setId("2");
        bean.setName("虾_" + UUID.randomUUID().toString().replace("-",""));
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/test/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(bean)))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下：{"code":0}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code", is(0)));
    }

    /**
     * 测试删除对象
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        //调用接口，传入添加的用户参数
        mockMvc.perform(get("/test/delete").param("id", "1"))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下{"code":0}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code", is(0)));

    }

    /**
     * 测试事务1（控制层不加@Transactional）
     * @throws Exception
     */
    @Test
    public void testTx() throws Exception {
        //调用接口，传入添加的用户参数
        mockMvc.perform(get("/test/tx").param("id", "1"))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下{"code":0}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code", is(500)));
    }

    /**
     * 测试事务2
     * @throws Exception
     */
    @Test
    public void testTx2() throws Exception {
        //调用接口，传入添加的用户参数
        mockMvc.perform(get("/test/tx2").param("id", "1"))
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下{"code":0}
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code", is(500)));
    }
}
