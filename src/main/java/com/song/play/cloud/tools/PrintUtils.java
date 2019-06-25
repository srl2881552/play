package com.song.play.cloud.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 打印工具类，输出异常信息到页�?
 * 
 * @author huoxinpeng
 *
 */
public class PrintUtils {

	private static Log logger = LogFactory.getLog(PrintUtils.class);

	
	
	
	
	
	/***
	 *  输出方法（支持jsonp）
	 * @param response
	 * @param obj
	 * @return
	 */
	public static String printJson(HttpServletRequest request,HttpServletResponse response,Object obj){
		String callback = request.getParameter("callback");
		response.setContentType("application/json");
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		JSONObject object = JSONObject.fromObject(obj,jsonConfig);
		String result = object.toString();
		
		//是否有callback
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(callback)) {
			result = callback + "("+result+")";
		}
		
		ajaxPrint(response, result);
		return result;
	}
	
	
	
	
	
	
	/**
	
	

	

	/**
	 * 输出ajax信息到前�?
	 * 
	 * @param response
	 * @param result
	 */
	public static void ajaxPrint(HttpServletResponse response, String result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * ���json��
	 * 
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String printResult(Object obj, String format) {
		String result = "";
		if ("xml".equalsIgnoreCase(format)) {
			XMLSerializer xmlSerializer = new XMLSerializer();

			if (obj instanceof List<?>) {
				result = xmlSerializer.write(JSONArray.fromObject(obj));
			} else {
				result = xmlSerializer.write(JSONObject.fromObject(obj));
			}
		} else {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				 public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					 return null;
				 }
				 public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					 if(value instanceof Date){
						 return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value);
					 } else {
						 return null;
					 }
				 }
			});
			jsonConfig.registerJsonValueProcessor(Long.class, new JsonValueProcessor() {
				
				@Override
				public Object processObjectValue(String key, Object value,
						JsonConfig jsonConfig) {
					// TODO Auto-generated method stub
					if(value instanceof Long){
						 return value.toString();
					 } else {
						 return null;
					 }
				}
				
				@Override
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					// TODO Auto-generated method stub
					 return null;
				}
			});
			if (obj instanceof List<?>) {
				result = JSONArray.fromObject(obj, jsonConfig).toString();
			} else {
				result = JSONObject.fromObject(obj, jsonConfig).toString();
			}
		}
		return result;
	}
	
	
	
}
