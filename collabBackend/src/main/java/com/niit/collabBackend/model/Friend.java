package com.niit.collabBackend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Friend implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int friendId;
	String loginname;
	String friendloginname;
	String status; //related to pending,accepted,none for request
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getLoginName() {
		return loginname;
	}
	public void setLoginName(String loginName) {
		this.loginname = loginName;
	}
	public String getFriendloginName() {
		return friendloginname;
	}
	public void setFriendloginName(String friendloginName) {
		this.friendloginname = friendloginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	 
}
