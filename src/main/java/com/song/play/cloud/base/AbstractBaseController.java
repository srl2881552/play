package com.song.play.cloud.base;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.song.play.cloud.entity.Users;
import com.song.play.cloud.tools.CommonConstant;
import com.song.play.cloud.tools.CookieUtil;
import com.song.play.cloud.tools.JedisManager;
import com.song.play.cloud.tools.PrintUtils;






public abstract class AbstractBaseController {


	@SuppressWarnings("serial")
	@InitBinder
	public void initDateBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat() {
					@Override
					public Date parse(String str) throws ParseException {
						if (str != null && str.length() > 0) {
							int n = str.length();
							String format;
							if (n == 4) {
								format = "yyyy";
							} else if (n == 7) {
								format = "yyyy-MM";
							} else if (n == 13) {
								format = "yyyy-MM-dd HH";
							} else if (n == 16) {
								format = "yyyy-MM-dd HH:mm";
							} else if (n == 19) {
								format = "yyyy-MM-dd HH:mm:ss";
							} else if (n == 5) {
								format = "HH:mm";
							} else if (n == 9) {
								format = "HH:mm:ss";
							} else {
								format = "yyyy-MM-dd";
							}
							return new SimpleDateFormat(format).parse(str);
						} else {
							return null;
						}
					}
				}, true));
	}
	
	
	public long getLongDate(java.util.Date date) {
		return date.getTime();
	}
	
	
	public long getLongDate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(date).getTime();
	}
	
	/**
	 * 输出对象
	 * @param obj 对象
	 */
	public void write(HttpServletResponse response, Object obj) {
		if (obj instanceof String
				|| obj instanceof Number
				|| obj instanceof Boolean) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", obj.toString());
			obj = map;
		}
    	PrintUtils.ajaxPrint(response, PrintUtils.printResult(obj, "json"));
	}
	/**
	 * 输出对象
	 * @param obj 对象
	 */
	public void writeList(HttpServletResponse response, int total, List<?> list) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		this.write(response, map);
	}
	/**
	 * 执行成功
	 */
	public void ok(HttpServletResponse response) {
		write(response, "{}");
	}
	
	public void ajax(HttpServletResponse response, String content, String ajaxContentType) throws IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType(ajaxContentType);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw e;
		}
	}
	
	/** The app context. */
	@Resource
	protected ApplicationContext appContext;
	
	/**
	 * <pre>
	 * Sets the app context.
	 * </pre>
	 * 
	 * @param appContext the app context
	 */
	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}
	
	/**
	 * <pre>
	 * Gets the i18n message.
	 * </pre>
	 * 
	 * @param msg the msg key.
	 * @return the i18n message
	 */
	public String getI18nMessage(String msg) {
		return getI18nMessage(msg, null);
	}
	
	/**
	 * <pre>
	 * Gets the i18n message.
	 * 
	 * The method defaults to get Chinese Locale message,
	 * but if end-user switches English, will return English message.
	 * </pre>
	 * 
	 * @param msg the msg key.
	 * @param args the message args
	 * @return the i18n message
	 */
	public String getI18nMessage(String msg, String[] args) {
		Locale locale = Locale.CHINESE;
		
		return getI18nMessage(msg, args, locale);
	}
	
	/**
	 * <pre>
	 * Gets the i18n message that indicated Locale.
	 * </pre>
	 * 
	 * @param msg the msg key.
	 * @param args the message args
	 * @param locale the i18n locale
	 * @return the i18n message
	 */
	private String getI18nMessage(String msg, String[] args, Locale locale) {
		return appContext.getMessage(msg, args, locale);
	}

    /**
     * liunan 格式化JSON（对应ERP带格式时间）
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	public static String formatJson(Map<String,Object> map){
        JsonConfig jsonConfig=new JsonConfig();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {
                return value;
            }
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
                if(value instanceof Date){
                    return simpleDateFormat.format((Date)value);
                }
                return value;
            }
        });
        List<Object> list=(List<Object>)map.get("list");
        if(list instanceof List<?>){
            StringBuffer buffer=new StringBuffer("{\"total\":"+map.get("total")+",\"rows\":");
            JSONArray jsonArray =JSONArray.fromObject(list,jsonConfig);
            buffer.append(jsonArray.toString());
            buffer.append("}");
            return buffer.toString();
        }else{
            return "";
        }
    }
    public Users getCurrentUser(HttpServletRequest request) {

		/** 获取cookie */
		Cookie cookie = CookieUtil.getCookie(request,CommonConstant.SESSION_ID);

		/** cookie中有session_id ,获取用户 */
		if (cookie != null && !StringUtils.isEmpty(cookie.getValue())) {

			/** session_id */
			String session_id = cookie.getValue();

			/** 从缓存服务器获取当前用户 */
			Users user = null;

			try {
				//从redis中取
				user = (Users)JedisManager.getObject("play:login:cookie:"+session_id);
			} catch (Exception e) {
				
			}

			return user;
		}
		return null;
	}
}
