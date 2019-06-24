package com.iTeam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * JSON数据格式转换
 * @author XieZhiHao
 *
 */
public class DataJsonValueProcessor implements JsonValueProcessor{

	private String format;
	private String result;
	
	public DataJsonValueProcessor(String format) {
		this.format = format;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if(value == null) {
			return "";
		} else if(value instanceof java.sql.Timestamp) {
			result = new SimpleDateFormat(format).format((java.sql.Timestamp)value);
		} else if(value instanceof java.util.Date){
			result = new SimpleDateFormat(format).format((java.util.Date)value);
		}
		return result;
	}
	
}
