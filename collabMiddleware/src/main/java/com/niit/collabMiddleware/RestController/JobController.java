package com.niit.collabMiddleware.RestController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabBackend.Dao.JobDao;
import com.niit.collabBackend.model.ApplyJob;
import com.niit.collabBackend.model.Job;

@RestController
public class JobController {
	
	@Autowired
	JobDao jobDao;
	
	// ---------------- Add Job -----------------------------------

		@PostMapping(value = "/addJob")
		public ResponseEntity<String> addJob(@RequestBody Job job) {
			job.setLastDateApply(new Date());
			if (jobDao.addJob(job)) {
				System.out.println("==========>Job details added successfully..");
				return new ResponseEntity<String>("Job Added- Success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Job insert failed", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		// -----------------list Jobs ---------------------------------

		@GetMapping(value = "/listJobs")
		public ResponseEntity<List<Job>> listJob() {
			List<Job> listJobs = jobDao.listAllJobs();
			System.out.println("==========> Job details retrieved<==========");
			if (listJobs.size() != 0) {
				return new ResponseEntity<List<Job>>(listJobs, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Job>>(listJobs, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		// ------------------Update Job -----------------------------------

		@PutMapping(value = "/updateJob/{jobId}")
		public ResponseEntity<String> updateJob(@PathVariable("jobId") int jobId, @RequestBody Job job) {
			System.out.println("Updating Job " + jobId);
			Job ujob = jobDao.getJob(jobId);
			if (ujob == null) {
				System.out.println("Job with jobId " + jobId + " Not Found");
				return new ResponseEntity<String>("Update Job Failue", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			ujob.setJobId(jobId);
			ujob.setCompany(job.getCompany());
			ujob.setLastDateApply(new Date());
			ujob.setJobDesc(job.getJobDesc());
			ujob.setJobDesignation(job.getJobDesignation());
			ujob.setLocation(job.getLocation());
			jobDao.updateJob(ujob);
			System.out.println("==========>Updated job details <===========");
			return new ResponseEntity<String>("Update Job Success", HttpStatus.OK);
		}

		// -----------------------Get Job ------------------------------------

		@GetMapping(value = "/getJob/{jobId}")
		public ResponseEntity<Job> getJob(@PathVariable("jobId") int jobId) {
			System.out.println("Get Job " + jobId);
			Job job = jobDao.getJob(jobId);
			if (job == null) {
				System.out.println("Job retrieval failure..");
				return new ResponseEntity<Job>(job, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				System.out.println("<==========Job retrieved========>");
				return new ResponseEntity<Job>(job, HttpStatus.OK);
			}
		}
		
		// ---------------- Apply Job -----------------------------------

			@PostMapping(value = "/applyJob")
			public ResponseEntity<String> addJob(@RequestBody ApplyJob applyJob) {
				applyJob.setApplyDate(new Date());
				applyJob.setJobId(3);
				if (jobDao.applyJob(applyJob)) {
					System.out.println("==========> ApplyJob details added successfully..");
					return new ResponseEntity<String>("ApplyJob Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Applyjob failed", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			// -----------------list applied Jobs ---------------------------------

			@GetMapping(value = "/listAppliedJobs")
			public ResponseEntity<List<ApplyJob>> listAppliedJob() {
				List<ApplyJob> listAppliedJobs = jobDao.getAllAppliedJobDetails();
				System.out.println("!!! Job details retrieved...............");
				if (listAppliedJobs.size() != 0) {
					return new ResponseEntity<List<ApplyJob>>(listAppliedJobs, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<ApplyJob>>(listAppliedJobs, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

}



