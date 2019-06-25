package com.iTeam.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iTeam.model.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


/**
 * 
 * @author guyue_NO1
 *
 */
public class PageUtil {
	
	/**
	 * 根据rows和page返回pageBean
	 * @param rows
	 * @param page
	 * @return
	 */
	public static PageBean getDefaultPage(String rows,String page) {
		if(StringUtil.isEmpty(rows)) {
			return new PageBean(1,50);
		}
		if(StringUtil.isEmpty(page)) {
			return new PageBean(1,Integer.parseInt(rows));
		}
		return new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
	}

	/**
	 * 根据pageBean里面的信息以及匹配信息返回Map
	 * @param pageBean
	 * @param key
	 * @param pattern
	 * @return
	 */
	public static Map<String,Object> getMapFromPage(PageBean pageBean,String key,String patternName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(key, StringUtil.formatString(patternName));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		return map;
	}
	
	public static Map<String,Object> getMapFromPage(PageBean pageBean,String key,int patternId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("key", patternId);
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		return map;
	}
	
	/**
	 * 处理日期格式
	 * @param propertyType
	 * @param list
	 * @param format
	 * @return
	 */
	public static JSONArray ProcessDataJsonValue(Class<? extends Date> propertyType,List<?> list,String format) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(propertyType, new DataJsonValueProcessor(format));
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray;
	}
}
