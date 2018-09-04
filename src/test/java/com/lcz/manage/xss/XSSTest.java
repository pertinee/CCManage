package com.lcz.manage.xss;

import com.lcz.manage.util.exception.CCException;
import com.lcz.manage.util.xss.SQLFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明: 测试SQL注入过滤
 *
 * @author lcz <lucz25053@hsjry.com>
 * @version CFM-V1.0
 * @date 2018年09月04日10:26:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class XSSTest {
    @Test
    public void testXSS() {
        try{
            Map<String, String> map = new HashMap<>();
            map.put("sidx", SQLFilter.sqlInject("insertorgId"));
            map.put("order", SQLFilter.sqlInject("id"));
        }catch(CCException cc){
            System.out.println(cc.getMsg());
        }
    }
}
