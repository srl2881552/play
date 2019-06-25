package com.song.play.cloud.tools;


public enum ResultCodeEnum{
	Success(0, "系统正常"),
	
	
	
	/****************************** 系统内部错误开始 **********************************/
	failure(-1, "系统异常"), 
	CITY_NOT_FOUND(1001,"城市名称没有获取到"),
	SHOP_NOT_FOUND(1002,"shopId没有获取到"),
	SEARCH_COMMENT_NOT_FOUND(1003,"搜索关键字没有"),
	SEARCH_PANEL(1018,"搜索面板不能为空"),
	SEARCH_TYPE(1006,"搜索类型没有"),
	CLIENT_VERSION_NOT_FOUND(1004,"客户端版本好没有"),
	PLATFORM_NOT_FOUND(1005,"平台信息没有"),
	USERNAME_IS_NULL(1006,"userName为空"),
	PASSWORD_IS_NULL(1007,"password为空"),
	USERNAME_NOT_EXIST(1008,"用户名不存在"),
	PASSWORD_ERR(1009,"密码错误"),
	ACTIVITYID_NULL(1010,"活动ID为空"),
	ACTIVITY_NOT_FOUND(1011,"无此活动"),
	ROOM_NO_NULL(1012,"房间号为空"),
	ROOM_USED(1013,"房间使用中"),
	ROOM_NOT_EXIST(1014,"房间不存在"),
	ROOM_JINBO(1015,"房间已禁播"),
	TIME_NOT_BEGIN(1016,"直播开始前5分钟可进入房间"),
	TIME_END(1017,"直播时间已结束"),
	COMPOSITEID(1018,"compositeId不能为空"),
	ID_NOT_FOUND(1019,"id不能为空"),
	INFO_NOT_FOUND(1020,"无此头条"),
	LNG_NOT_FOUND(1021,"longitude 为空"),
	LAT_NOT_FOUND(1022,"latitude 为空"),
	CATE_ID_NOT_FOUND(1023,"分类ID不存在"),
	DEPARTMENT_NOT_FOUND(1024,"科室ID不存在"),
	DOCTOR_NOT_FOUND(1025,"医生ID不存在"),
	SCHEID_NOT_FOUND(1026,"scheId不存在"),
	USERMOBILE_NOT_FOUND(1027,"登录用户手机号为空"),
	USERID_IS_NULL(1028,"userid为空"),
	MOBILE_NOT_FOUND(1029,"就诊人手机号为空"),
	OREDER_ID_NOT_FOUND(1030,"预约单ID不存在"),
	STAR(1031,"星级不能为空"),
	CONTEXT_NOT_FOUND(1032,"内容不能为空"),
	PATIENT_ID_NOT_FOUND(1033,"就诊人ID不存在"),
	MOBILE_FORMAT_ERROR(1034,"手机格式错误"),
	SEX_NOT_FOUND(1035,"性别不能为空"),
	suite(1036,"店铺类型不存在"),
	SHOP_LOCATE_FAILED(100001, "定位失败"),
	POPAD_FAILED(100015, "此活动只限新注册用户"),
	POPAD_REPEAT(100016, "此活动您已参加过了"),
	WEILANWAI(100002, "围栏外"),
	SUITE_NULL(100003, "店铺类型为空"),
	SUITE_ERR(100004, "店铺类型错误"),
	SUITE_NOT_MATCH_ERR(100005, "传入店铺类型和定位得到的店铺类型不一致"),
	COMMENT_ERROR(1000006, "发言过快"),
	USER_ERROR(1000007, "用户不存在"),
	PUSH(1000009, "PUSH状态没传递"),
	INTERACTION(1000010, "互动状态没传递"),
	DISCOUNT(1000011, "优惠状态没传递"),
	ORDER(1000012, "订单状态没传递"),
	ACCOUNT(1000013, "账号状态没传递"),
	PARAM_NULL_ERROR(1000014, "参数为空"),
	PARAM_NULL_OR_TYPE_ERROR(1000008, "参数为空或类型错误"),
	TYPE_ERROR(1000009, "推荐类型不能为空"),
	UNIQE(1000010, "推荐用户唯一标识不能为空"),
	VERSION_NULL(1000012, "版本号为空"),
	CONTENT_NULL(1000019, "回复内容为空"),
	SAVE_FAILED(1000018, "回复内容保存失败"),
	LIST_NULL(1000017, "回复列表查询失败"),
	THIRD_VOUCHER_FAILED(1000016, "更新三方券领取状态失败"),
	ADD_NOTE_FAILED(1000015, "领取记录表添加数据失败"),
	PRODUCT_IDS(1000011, "推荐商品ID不能为空"),
	brandId(1000020, "品牌ID不能为空"),
	USER_NEW(1000022, "该活动仅限新用户"),
	USER_OLD(1000023, "该活动仅限老用户"),
	NO_CHANCE(1000025, "机会用完"),
	NO_COIN(1000026, "叮当币用完"),
	NO_SHARE_USER(1000027, "分享者信息不能为空"),
	NO_WX_ID(1000028, "微信ID不能为空"),
	NO_WX_BIND(1000029, "关注公众号但没绑定用户信息不能为空"),
	WX_BIND(1000030, "公众号绑定用户信息不能为空"),
	NO_VOUCHER(1000031, "券抢光了"),
	VOUCHER_ERR(1000032, "用户绑定券异常"),
	NO_VOUCHER_CODE(1000033, "没有此券编码"),
	ACT_NOT_EXPIRE(1000021, "该活动已过期"),
	ACT_NOT_START(1000035, "该活动未开始"),
	ARTICLEID_NOT_NULL(1000036,"文章ID不能为NULL"),
	GUESS_MATCH_END(1000037,"竞猜场次已结束"),
	GUESS_DATE_NO(1000038,"当前日期没有竞猜场次"),
	GUESS_MATCH_NO(1000039,"没有当前竞猜场次"),
	MATCH_NOT_START(1000040,"当前竞猜场次还未开始"),
	BET_EXIST(1000041,"已经参与过"),
	USERNAME_NULL(1000042,"用户名为空"),
	ADDRESS_NULL(1000043,"地址为空"),
	SAVE_TIME_NULL(1000044,"时间为空"),
	NOT_CONTINUITY_SUBMIT(1000045,"不允许连续提交"),
	USER_IS_HAVE(1000046,"用户已经存在");
	/*********************************** 支付结束 ***********************************/
	
	
	
	private int index;

	private String message;

	private ResultCodeEnum(int index, String message) {
		this.index = index;
		this.message = message;
	}

	
	public int getIndex() {
		return this.index;
	}
	public String getMessage() {
		return this.message;
	}
	public static ResultCodeEnum getByIndex(int index) {
		for (ResultCodeEnum os : ResultCodeEnum.values()) {
			if (os.getIndex() == index) {
				return os;
			}
		}
		return null;
	}

	
	
	public void setMsg(String msg) {
		this.message = msg;
	}


	

}
