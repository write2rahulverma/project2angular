package com.niit.collabBackend.Dao;

import java.util.List;

import com.niit.collabBackend.model.Friend;
import com.niit.collabBackend.model.UserDetail;

public interface FriendDao {
	
	public boolean sendFriendRequest(Friend friend);
	public boolean deleteFriendRequest(int friendId);
	public List<UserDetail>showSuggestedFriend(String loginname);
	public List<Friend>showAllFriend(String loginname);
	public List<Friend>showRequestPendingList(String loginname);
	public boolean acceptFriendRequest(int friendId);

}
