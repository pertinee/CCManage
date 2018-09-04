package com.lcz.manage.util;

import com.lcz.manage.util.xss.SQLFilter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author luchunzhou
 */
public class Query extends LinkedHashMap<String, Object> {

    private static final String OFFSET = "offset";
    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    private static final String SIDX = "sidx";
    private static final String ORDER = "order";

	//当前页码
    private int page;
    //每页条数
    private int limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get(PAGE).toString());
        this.limit = Integer.parseInt(params.get(LIMIT).toString());
        this.put(OFFSET, (page - 1) * limit);
        this.put(PAGE, page);
        this.put(LIMIT, limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = params.get(SIDX).toString();
        String order = params.get(ORDER).toString();
        this.put(SIDX, SQLFilter.sqlInject(sidx));
        this.put(ORDER, SQLFilter.sqlInject(order));
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
