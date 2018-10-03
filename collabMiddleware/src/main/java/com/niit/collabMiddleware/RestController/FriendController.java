package com.niit.collabMiddleware.RestController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabBackend.Dao.FriendDao;
import com.niit.collabBackend.model.Friend;
import com.niit.collabBackend.model.UserDetail;

@RestController
public class FriendController {

	@Autowired
	FriendDao friendDao;
	
	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFreindRequset(@RequestBody Friend friend)
	{
		if(friendDao.sendFriendRequest(friend)) {
			return new ResponseEntity<String>("Friend Request Sent....!!",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Fail to sent friend Request",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDao.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("friend request deleted..!!",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to delete friend Request",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDao.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Friend Request accepted..!!",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("fail to accept friend request",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/showAllFriends")
	public ResponseEntity<List<Friend>> showAllFriends(HttpSession session)
	{
		List<Friend> listAllFriends=friendDao.showAllFriend("");
		if(listAllFriends.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listAllFriends,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listAllFriends,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/showPendingRequest")
	
	public ResponseEntity<List<Friend>> showPendingRequest(HttpSession session)
	{
		
		List<Friend> listPendingRequest=friendDao.showRequestPendingList("");
		if(listPendingRequest.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listPendingRequest,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listPendingRequest,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/showSuggestedFriends")
	public ResponseEntity<List<UserDetail>> showSuggestedFriends(HttpSession session){
		
		String loginname=((UserDetail)session.getAttribute(("userRecord"))).getEmail();
		List<UserDetail> listSuggestedFriend=friendDao.showSuggestedFriend(loginname);
		
		if(listSuggestedFriend.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriend, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
