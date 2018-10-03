package com.niit.collabBackend.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabBackend.Dao.BlogDao;
import com.niit.collabBackend.model.Blog;
import com.niit.collabBackend.model.BlogComment;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao  {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addBlog(Blog blog) {
		try {
			 sessionFactory.getCurrentSession().save(blog);
			
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
		
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try {
			 sessionFactory.getCurrentSession().update(blog);
			
			 return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
		
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		try {
			 sessionFactory.getCurrentSession().delete(blog);
			
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public Blog getBlog(int blogId) {
		try {
		Session session = sessionFactory.openSession();
		Blog blog= session.get(Blog.class, blogId);
		return blog;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
	}

	@Override
	public boolean approveBlog(Blog blog) {
		try {
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
			
		}
	}

	@Override
	public boolean rejectBlog(Blog blog) {
		try {
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Blog> listBlog(String userName) {
		try {
			Session session =sessionFactory.openSession();
			session.beginTransaction();
			List<Blog> blogList =new ArrayList<Blog>();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("From Blog where username = :username").setString("username", userName );
			blogList = query.list();
			return blogList;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
	}

	@Override
	public boolean incrementLike(Blog blog) {
		try 
		{
			blog.setLikes(blog.getLikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception Occured:"+e);
			return false;
		}
	}

	@Transactional
	public boolean addBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	@Override
	public BlogComment getBlogComment(int commentId) {
		try {
			Session session =sessionFactory.openSession();
			BlogComment blogComment=session.get(BlogComment.class, commentId);
			return blogComment;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<BlogComment> listBlogComments(int blogid) {
		Session session=sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("from BlogComment where blogId=:blogId");
		query.setParameter("blogId", new Integer(blogid));
		@SuppressWarnings("unchecked")
		List<BlogComment> listBlogComment=query.list();
		return listBlogComment;
	}

}
