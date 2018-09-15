package com.lcz.manage.util.redis;

/**
 * Redis所有Keys
 *
 * @author luchunzhou
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }

    public static String getSysDictKey(String key){
        return "sys:dict:" + key;
    }
}
