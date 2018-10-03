package com.niit.collabBackend.Dao;

import java.util.List;

import com.niit.collabBackend.model.UserDetail;

public interface UserDao {
	public boolean addUser(UserDetail user);
	public boolean updateUser(UserDetail user);
	public boolean deleteUser(UserDetail user);
	public UserDetail getUser(String userId);
	public boolean checkLogin(UserDetail user);
	public boolean updateOnlineStatus(String status, UserDetail user);
	public List<UserDetail> listUser();
	

}
