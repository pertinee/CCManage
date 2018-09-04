package com.lcz.manage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author luchunzhou
 */
public class R extends HashMap<String, Object> {

	private static final String CODE = "code";
	private static final String MSG = "msg";
	private static final Integer FIVE_HUNDRED = 500;
	private static final String UNKNOWN_EXCEPTION = "未知异常，请联系管理员";
	
	public R() {
		put(CODE, 0);
	}
	
	public static R error() {
		return error(FIVE_HUNDRED, UNKNOWN_EXCEPTION);
	}
	
	public static R error(String msg) {
		return error(FIVE_HUNDRED, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put(CODE, code);
		r.put(MSG, msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put(MSG, msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}


	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
