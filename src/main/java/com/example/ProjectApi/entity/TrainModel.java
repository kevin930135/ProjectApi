package com.example.ProjectApi.entity;

import javax.ws.rs.FormParam;

import org.springframework.web.multipart.MultipartFile;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class TrainModel {
	// 接圖片名稱for路徑、userId for 建模id ->改list.txt
	public TrainModel() {

	}

	private String userId;
	private String photoName;
	private byte[] photo;

	@FormParam("photoName")
	@PartType("text/plain")
	public String getphotoName() {
		return photoName;
	}

	@FormParam("photoName")
	@PartType("text/plain")
	public void setphotoName(String photoName) {
		this.photoName = photoName;
	}

	public byte[] getphoto() {
		return photo;

	}

	public String getUserId() {
		return userId;
	}

	@FormParam("userId")
	@PartType("text/plain")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@FormParam("photo")
	@PartType("application/octet-stream")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@FormParam("photo")
	@PartType("application/octet-stream")
	public void setphoto(byte[] photo) {
		this.photo = photo;
	}

}
