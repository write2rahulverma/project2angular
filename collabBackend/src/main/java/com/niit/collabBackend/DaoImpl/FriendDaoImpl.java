package com.niit.collabBackend.DaoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabBackend.Dao.FriendDao;
import com.niit.collabBackend.model.Friend;
import com.niit.collabBackend.model.UserDetail;

@SuppressWarnings("deprecation")
@Repository(value="friendDao")
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDaoImpl(SessionFactory sf) {
		super();
		this.sessionFactory=sf;
	}

	@Transactional
	public boolean sendFriendRequest(Friend friend) {
		try {
			System.out.println("Into sendFriendRequest");
			friend.setStatus("P");
			sessionFactory.getCurrentSession().save(friend);
			return true;
		}
		catch(Exception e) {
		return false;
	}
	}
	
	@Transactional
	public boolean deleteFriendRequest(int friendId) {
         try {
        	 System.out.println("Into deleteFriendRequest");
 			Session session = sessionFactory.openSession();
 			Friend friend = (Friend) session.get(Friend.class, friendId);
 			if(friend.getStatus()=="P") {
 				sessionFactory.getCurrentSession().delete(friend);
 				session.close();
 				System.out.println("Deleted friendRequest if if: "+friendId);
 			}
 			else {
 				System.out.println("Friend Request already accepted..!!");
 			}
 			return true;
         }
 			catch(Exception e) {
 				return false;
 			}
         }
	

	public List<UserDetail> showSuggestedFriend(String loginname) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings({ "rawtypes" })
		Query query = session
				.createSQLQuery("select * from userdetail where email not in (select friendloginname from friend where loginname='"+loginname+"' and loginname != '"+loginname+"')");
		@SuppressWarnings({ "unchecked" })
		List<UserDetail> suggestFriend = (List<UserDetail>) query.list();
		return suggestFriend;
	}


	
	@SuppressWarnings("deprecation")
	public List<Friend> showAllFriend(String loginname) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Friend where loginname =:currentuser and status='A'");
		query.setParameter("currentuser", loginname);
		@SuppressWarnings("unchecked")
		List<Friend> listFriends = (List<Friend>) query.list();
		return listFriends;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Friend> showRequestPendingList(String loginname) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Friend where loginname =:currentuser and status='P'");
		query.setParameter("currentuser", loginname);
		@SuppressWarnings("unchecked")
		List<Friend> listFriends = (List<Friend>) query.list();
		return listFriends;
	}

	@Transactional
	public boolean acceptFriendRequest(int friendId) {
		try {
			Session session = sessionFactory.openSession();
			Friend friend = (Friend) session.get(Friend.class, friendId);
			friend.setStatus("A");
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (Exception e) {
			return false;
	}
	}
}

