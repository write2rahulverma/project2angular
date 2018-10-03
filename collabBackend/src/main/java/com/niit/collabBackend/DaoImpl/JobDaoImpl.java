package com.niit.collabBackend.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabBackend.Dao.JobDao;
import com.niit.collabBackend.model.ApplyJob;
import com.niit.collabBackend.model.Job;

@Repository("jobDao")
@Transactional
@SuppressWarnings({"unchecked", "rawtypes", })
public class JobDaoImpl implements JobDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return false;
		}
	}

	@Override
	public List<Job> listAllJobs() {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			List<Job> jobList = new ArrayList<Job>();
			Query query = session.createQuery("FROM Job");
			jobList = query.list();
			return jobList;
		} catch (Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
	}

	@Override
	public Job getJob(int jobId) {
		try {
			Session session = sessionFactory.openSession();
			Job job =session.get(Job.class, jobId);
					return job;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
		
	}

	@Override
	public boolean applyJob(ApplyJob applyJob) {
		try {
			sessionFactory.getCurrentSession().save(applyJob);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return true;
		}
	}

	@Override
	public List<ApplyJob> getAllAppliedJobDetails() {
		try {
			Session session= sessionFactory.openSession();
			session.beginTransaction();
			List<ApplyJob> appliedjobList=new ArrayList<ApplyJob>();
			Query query = session.createQuery("FROM ApplyJob");
			appliedjobList = query.list();
			return appliedjobList;
		}
		catch(Exception ex) {
			System.out.println("Exception Occured:"+ex);
			return null;
		}
	}

}
