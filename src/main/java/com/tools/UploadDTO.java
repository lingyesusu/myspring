package com.tools;


public class UploadDTO {
	
	private String imgUrl;
	
	private String thumbUrl;
	
	public UploadDTO(String imgUrl, String thumbUrl) {
		super();
		this.imgUrl = imgUrl;
		this.thumbUrl = thumbUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	
	
}
