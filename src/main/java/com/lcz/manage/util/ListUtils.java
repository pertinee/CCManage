package com.lcz.manage.util;

import com.lcz.manage.util.exception.CCException;
import org.apache.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明:List集合工具类
 *
 * @author luchunzhou
 * @date 2018年10月21日 下午2:31:30
 */
public class ListUtils {

    private static final Logger logger = Logger.getLogger(ListUtils.class);

    /**
     * List转换为map
     *
     * @param list  数据源
     * @param idFieldName   主键标识字段，作为返回map的key
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> Map<K, List<T>> list2Map(List<T> list, String idFieldName) {
        Map<K, List<T>> result = new HashMap<>();
        for (T item : list) {
            Object key = obtainIdValue(item, idFieldName);
            if (key == null) {
                continue;
            }
            List<T> optList = result.get(key);
            if (optList == null) {
                optList = new ArrayList<T>();
            }
            optList.add(item);
            result.put((K) key, optList);
        }
        return result;
    }

//    public static <T> Map<Long, List<T>> list2Map(List<T> list, String idFieldName) {
//        Map<Long, List<T>> result = new HashMap<>();
//        for (T item : list) {
//            Object key = obtainIdValue(item, idFieldName);
//            if (key == null) {
//                continue;
//            }
//            List<T> optList = result.get(key);
//            if (optList == null) {
//                optList = new ArrayList<T>();
//            }
//            optList.add(item);
//            result.put((Long) key, optList);
//        }
//        return result;
//    }


    /**
     * 根据字段名获取对象中主键
     *
     * @throws
     * @parameter：@param item 对象
     * @parameter：@param idFieldName 字段名
     * @parameter：@return
     * @return：Long
     */
    public static <T> Object obtainIdValue(T item, String idFieldName) {
        try {
            Class<? extends Object> clazz = item.getClass();
            Field field = clazz.getDeclaredField(idFieldName);
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
            Method method = descriptor.getReadMethod();
            Object valObj = method.invoke(item);
            return valObj == null ? null : valObj;
        } catch (Exception e) {
            throw new CCException("获取对象主键失败", e);
        }
    }
}

