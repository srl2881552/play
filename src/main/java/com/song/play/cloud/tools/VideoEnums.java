package com.song.play.cloud.tools;

import java.util.LinkedHashMap;
import java.util.Map;

public enum VideoEnums {

	mkv(1, "mkv"), avi(3, "avi"), rm(4, "rm"),rmvb(5,"rmvb"),wmv(6, "wmv");
	private int code;
	
	/**状态标题**/
	private String value;
	
	/**
	 * 
	 * 构造函数
	 *
	 * @param code 状态编码
	 * @param title 状态标题
	 */
	private VideoEnums(int code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
	 * 根据编码获取订单状态
	 * <pre>
	 * @param code  状态编码
	 * @return
	 * OrderStatus.getStatus()<BR>
	 * <P>Author : zhumingzhou </P>  
	 * <P>Date : 2014-12-13 </P>
	 * </pre>
	 */
	public static VideoEnums getPaymentSource(int code) {
		for(VideoEnums status : VideoEnums.values()) {
			if(code == status.getCode())
				return status;
		}
		return null;
	}
	/**
	 * 根据编码获取订单状态
	 * <pre>
	 * @param code  状态编码
	 * @return
	 * OrderStatus.getStatus()<BR>
	 * <P>Author : zhumingzhou </P>  
	 * <P>Date : 2014-12-13 </P>
	 * </pre>
	 */
	public static VideoEnums getPaymentSource(String value) {
		for(VideoEnums status : VideoEnums.values()) {
			if(value.equals(status.getValue()))
				return status;
		}
		return null;
	}

	/**
	 * @return int 取得域 code 的方法。
	 */	
	 
	
	/**
	 * 所有支付状态
	 * @return
	 */
	public static Map<String,String> getAllPayStatus() {
		Map<String,String> map = new LinkedHashMap<String,String>();
		for(VideoEnums payStatus : VideoEnums.values()) {
			map.put(payStatus.name(), payStatus.getValue());
		}
		return map;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
