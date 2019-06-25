package com.song.play.cloud.tools;

import org.springframework.web.servlet.HandlerExceptionResolver;


/**
 * 业务异常.
 * <p>
 * 1.抛出业务异常后由{@linkplain HandlerExceptionResolver}进行解析.
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 4620872602332366142L;

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BusinessException(ResultCodeEnum resultCode) {
		super(resultCode.getMessage());
		this.code = resultCode.getIndex();
	}

	public BusinessException(ResultCodeEnum resultCode, Throwable cause) {
		super(resultCode.getMessage(), cause);
		this.code = resultCode.getIndex();
	}
	
	public BusinessException(ResultCodeEnum resultCode, String message) {
		super(message);
		this.code = resultCode.getIndex();
	}

	public BusinessException(ResultCodeEnum resultCode, String message, Throwable cause) {
		super(message, cause);
		this.code = resultCode.getIndex();
	}

	/**
	 * 异常编码.
	 */
	private int code = ResultCodeEnum.failure.getIndex();
	
	private Object data;

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
