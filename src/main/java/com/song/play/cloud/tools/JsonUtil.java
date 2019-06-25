package com.song.play.cloud.tools;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	private static ObjectMapper objectMapper2 = new ObjectMapper();
	
	private static ObjectMapper objectMapper3 = new ObjectMapper();

	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

		objectMapper2.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper2.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}

	public static String writeValueAsString(Object obj) {
		return writeValueAsString(obj, false);
	}

	public static String writeValueAsString(Object obj, boolean ignoreNullField) {
		try {
			if (ignoreNullField) {
				return objectMapper.writeValueAsString(obj);
			} else {
				return objectMapper2.writeValueAsString(obj);
			}
		} catch (Exception e) {
			throw new RuntimeException("failed translate object to json", e);
		}
	}

	public static <T> T readValue(String json, Class<T> type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (Exception e) {
			throw new RuntimeException("failed read object from json", e);
		}
	}

	public static <T> T readValue(JsonNode jsonNode, Class<T> type) {
		try {
			return objectMapper.readValue(writeValueAsString(jsonNode), type);
		} catch (Exception e) {
			throw new RuntimeException("failed read object from json", e);
		}
	}

	public static <T> List<T> readValue(String json, Class<?> listClass, Class<T> typeClass) {
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(listClass, typeClass);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException("failed read object from json", e);
		}
	}
	
	public static Object readValue(String json, JavaType javaType) {
		try {
			return objectMapper.readValue(json, javaType);
		} catch (Exception e) {
			throw new RuntimeException("failed read object from json", e);
		}
	}

	
	
	public static JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
		return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}

	public static JavaType constructParametricType(Class<?> parametrized, JavaType... parameterClasses) {
		return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	}
	
	public static <T> T readValue(String json, Class<T> type, boolean isAllowNoField) {
		try {
			if (!isAllowNoField) {
				return objectMapper.readValue(json, type);
			} else {
				return objectMapper3.readValue(json, type);					
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("failed read object from json", e);
		}
	}
	public static void main(String[] args) {
		String str="172.16.0.11|172.16.10.51|0.016|223.104.212.167|-|-|[06/Jul/2017:08:53:32 +0800]|GET /delivery/rest.htm?login=2022370030&method=ddky.delivery.user.login&pwd=96e79218965eb72c92a549dd5a330112&sign=C1F487715C42D93800F1AEE8683E3257&t=2017-07-06%2008%3A53%3A31&v=1.0 HTTP/1.1|200|335|-|DDDeliver/3.2 (iPhone; iOS 10.3.2; Scale/2.00) ";
		System.out.println(str.substring(str.indexOf("login="), str.indexOf("login=")+15));
	}
}
