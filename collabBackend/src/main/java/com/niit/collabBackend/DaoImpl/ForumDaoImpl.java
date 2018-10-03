package com.niit.collabBackend.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabBackend.Dao.ForumDao;
import com.niit.collabBackend.model.Forum;
import com.niit.collabBackend.model.ForumComment;

@Repository("forumDao")
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class ForumDaoImpl implements ForumDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public Forum getForum(int forumId) {
		try {
			Session session = sessionFactory.openSession();
			Forum forum =session.get(Forum.class, forumId);
					return forum;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
		
	}

	@Override
	public List<Forum> listForum(String userName) {
		try {
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		List<Forum> blogList =new ArrayList<>();
		Query query = session.createQuery("From Forum where username = :username").setString("username", userName );
		blogList = query.list();
		return blogList;
	}
	catch(Exception ex) {
		System.out.println("Exception Occured:"+ex);
		return null;
	}
}

	@Override
	public boolean approveForum(Forum forum) {
		try {
			forum.setStatus("A");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public boolean rejectForum(Forum forum) {
		try {
			forum.setStatus("NA");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Transactional
	public boolean addForumDiscussion(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteForumDiscussion(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		}
		catch(Exception e) {
		return false;
		}
	}

	@Override
	
	public ForumComment getForumDiscussion(int discussionId) {
		try {
		Session session= sessionFactory.openSession();
		ForumComment forumComment=session.get(ForumComment.class, discussionId);
		return forumComment;
		}
		catch(Exception e) {
		return null;
	}
	}

	
	public List<ForumComment> listForumDiscussion(int forumid) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ForumComment where forumId=:forumId");
		query.setParameter("forumId", new Integer(forumid));
		List<ForumComment> listForumComments = query.list();
		return listForumComments;
	}

}
