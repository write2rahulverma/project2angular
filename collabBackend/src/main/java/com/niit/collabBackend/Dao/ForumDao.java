package com.niit.collabBackend.Dao;

import java.util.List;

import com.niit.collabBackend.model.Forum;
import com.niit.collabBackend.model.ForumComment;

public interface ForumDao {
	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public Forum getForum(int forumId);
	public List<Forum> listForum(String userName);
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);

	 public boolean addForumDiscussion(ForumComment forumComment);
	    public boolean deleteForumDiscussion(ForumComment forumComment);
	    public ForumComment getForumDiscussion(int discussionId);
	    public List<ForumComment> listForumDiscussion(int forumid);
}
