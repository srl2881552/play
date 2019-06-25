package com.song.play.cloud.entity;

import java.util.Date;



public class Desktop extends BaseEntity{
	
	private String id;
	
	private String userId;
	
	private String nickName;
	
	private String title;
	
	private String about;
	
	private String cateId;
	
	private String cateName;
	
	private String rtmp;
	
	private String stream;
	
	private Integer status;
	
	private String message;
	
	private String createAt;
	
	private String topImage;
	
	private String imageName;
	
	private Integer type;
	
	private String videoLength;
	
	private Long chick;
	
	
	
	public String getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(String videoLength) {
		this.videoLength = videoLength;
	}
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	public String getRtmp() {
		return rtmp;
	}
	public void setRtmp(String rtmp) {
		this.rtmp = rtmp;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getTopImage() {
		return topImage;
	}
	public void setTopImage(String topImage) {
		this.topImage = topImage;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Long getChick() {
		return chick;
	}
	public void setChick(Long chick) {
		this.chick = chick;
	}
	
	
	
}
