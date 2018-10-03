package com.niit.collabBackend.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabBackend.Dao.UserDao;
import com.niit.collabBackend.model.UserDetail;

@Repository("userDao")
@SuppressWarnings({ "unchecked", "rawtypes",  })
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return false;
		}
	}

	@Override
	public boolean updateUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return false;
		}
	}

	@Override
	public boolean deleteUser(UserDetail user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return false;
		}
	}

	@Override
	public UserDetail getUser(String userId) {
		try {
			Session session = sessionFactory.openSession();
			UserDetail user = session.get(UserDetail.class, userId);
			return user;
		} catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return null;
		}
	}

	@Override
	public boolean checkLogin(UserDetail user) {
		try {
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("From UserDetail where email=:email and password=:password");
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			UserDetail userDetails = (UserDetail) query.list().get(0);
			session.close();
			if (userDetails == null) {
				return false;
			} else {

				return true;
			}
		} catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return false;
		}
	}

	@Override
	public boolean updateOnlineStatus(String status, UserDetail user) {
		try {
			user.setIsOnline(status);
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		 catch (Exception ex) {
				System.out.println("Exception Occured:" + ex);
				return false;
			}
	}

	@Override
	public List<UserDetail> listUser() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<UserDetail> userList = new ArrayList<UserDetail>();
			Query query = session.createQuery("FROM UserDetail");
			userList = query.list();
			return userList;
		}
		catch (Exception ex) {
			System.out.println("Exception Occured:" + ex);
			return null;
		}
		
	}

}
