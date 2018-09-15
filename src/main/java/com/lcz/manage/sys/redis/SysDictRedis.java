package com.lcz.manage.sys.redis;

import com.lcz.manage.sys.bean.SysDictBean;
import com.lcz.manage.util.redis.RedisKeys;
import com.lcz.manage.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Redis数据字典
 *
 * @author luchunzhou
 */
@Component
public class SysDictRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysDictBean dict) {
        if(dict == null){
            return ;
        }
        String key = RedisKeys.getSysDictKey(dict.getId());
        redisUtils.set(key, dict);
    }

    public void delete(String dictId) {
        String key = RedisKeys.getSysDictKey(dictId);
        redisUtils.delete(key);
    }

    public SysDictBean get(String dictId){
        String key = RedisKeys.getSysDictKey(dictId);
        return redisUtils.get(key, SysDictBean.class);
    }
}
