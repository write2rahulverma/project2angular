package com.niit.collabBackend.Dao;

import java.util.List;

import com.niit.collabBackend.model.ApplyJob;
import com.niit.collabBackend.model.Job;

public interface JobDao {
	public boolean addJob(Job job);
	public boolean updateJob(Job job);
	public boolean deleteJob(Job job);
	public List<Job> listAllJobs();
	public Job getJob(int jobId);
    public boolean applyJob(ApplyJob applyJob);
    public List<ApplyJob> getAllAppliedJobDetails();
	

}
