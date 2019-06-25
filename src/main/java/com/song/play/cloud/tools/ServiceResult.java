package com.song.play.cloud.tools;

/**
 * 通用返回值
 */
public class ServiceResult {

	public int code;

	public String msg;

	public Object result;

	public ServiceResult() {
		this(ResultCodeEnum.Success);
	}

	public ServiceResult(ResultCodeEnum resultCode) {
		this.code = resultCode.getIndex();
		this.msg = resultCode.getMessage();
	}

	public ServiceResult(BusinessException e) {
		this.code = e.getCode();
		this.msg = e.getMessage();
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return this.result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceResult [");
		builder.append("code=").append(code).append(", ");
		if (msg != null)
			builder.append("msg=").append(msg).append(", ");
		if (result != null)
			builder.append("result=").append(result);
		builder.append("]");
		return builder.toString();
	}

	public String toJson() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("code:").append(code).append(", ");
		if (msg != null)
			builder.append("msg:").append(msg).append(", ");
		if (result != null)
			builder.append("result:").append(result);
		builder.append("}");
		return builder.toString();
	}
}