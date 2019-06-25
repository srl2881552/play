package com.song.play.cloud.entity;

import java.util.Date;



public class UserAccountItem extends BaseEntity{

	
	private String id;
	
	private String userId;
	
	private Long accountCoin;
	
	private Long coin;
	
	private Integer type;
	
	private String billId;
	
	private String remark;
	
	private Date createAt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getAccountCoin() {
		return accountCoin;
	}
	public void setAccountCoin(Long accountCoin) {
		this.accountCoin = accountCoin;
	}
	public Long getCoin() {
		return coin;
	}
	public void setCoin(Long coin) {
		this.coin = coin;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
