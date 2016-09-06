package com.project.vo;

import java.io.Serializable;

public class UserVo implements Serializable{
	
	private int uid;
	
	private String uNo;
	
	private String userName;
	
	private String userPassWord;
	
	private String realName;
	
	private String phone;
	
	private String email;
	
	private String QQ;
	
	private String weChatNo;
	
	private String emergencyContactPerson;
	
	private String emergencyContactPhone;
	
	private String dname;
	
	private String entryTime;
	
	private int iseffective;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getuNo() {
		return uNo;
	}

	public void setuNo(String uNo) {
		this.uNo = uNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassWord() {
		return userPassWord;
	}

	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getWeChatNo() {
		return weChatNo;
	}

	public void setWeChatNo(String weChatNo) {
		this.weChatNo = weChatNo;
	}

	public String getEmergencyContactPerson() {
		return emergencyContactPerson;
	}

	public void setEmergencyContactPerson(String emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	
	
	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public int getIseffective() {
		return iseffective;
	}

	public void setIseffective(int iseffective) {
		this.iseffective = iseffective;
	}

	

	public UserVo(int uid, String uNo, String userName, String userPassWord,
			String realName, String phone, String email, String qQ,
			String weChatNo, String emergencyContactPerson,
			String emergencyContactPhone, String dname, String entryTime,
			int iseffective) {
		this.uid = uid;
		this.uNo = uNo;
		this.userName = userName;
		this.userPassWord = userPassWord;
		this.realName = realName;
		this.phone = phone;
		this.email = email;
		QQ = qQ;
		this.weChatNo = weChatNo;
		this.emergencyContactPerson = emergencyContactPerson;
		this.emergencyContactPhone = emergencyContactPhone;
		this.dname = dname;
		this.entryTime = entryTime;
		this.iseffective = iseffective;
	}

	public UserVo() {}
	
	
	
}
