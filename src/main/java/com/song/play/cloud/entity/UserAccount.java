package com.song.play.cloud.entity;

import java.util.Date;






public class UserAccount extends BaseEntity{

	
	private String id;
	
	private String userId;
	
	private Long coin;
	
	private Date createAt;
	
	private Date changeAt;
	private Long version;
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
	public Long getCoin() {
		return coin;
	}
	public void setCoin(Long coin) {
		this.coin = coin;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Date getChangeAt() {
		return changeAt;
	}
	public void setChangeAt(Date changeAt) {
		this.changeAt = changeAt;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
}
