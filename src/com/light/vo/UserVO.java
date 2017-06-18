package com.light.vo;

import java.io.Serializable;

public class UserVO implements Serializable {
	
	private static UserVO userVO = new UserVO();
	
	public static UserVO getInstance(){
		return userVO;
	}
	
	private UserVO(){
	}
	
	String id = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	
}
