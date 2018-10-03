package com.niit.collabMiddleware.RestController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabBackend.Dao.ForumDao;
import com.niit.collabBackend.model.Forum;
import com.niit.collabBackend.model.ForumComment;

@RestController
public class ForumController {

	@Autowired
	ForumDao forumDao;

	// ---------------- Add Forum -----------------------------------

	@PostMapping(value = "/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum) {
		System.out.println("Inside insert forum");
		forum.setCreateDate(new Date());
		forum.setStatus("A");
		forum.setUserName("akivkhan");
		if (forumDao.addForum(forum)) {
			return new ResponseEntity<String>("Forum Added- Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Forum insert failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    //---------------------------------List Forum----------------------------------------------
	
	@GetMapping(value = "/listForums")
	public ResponseEntity<List<Forum>> listForum() {
		List<Forum> listForums = forumDao.listForum("akivkhan");
		try {
			if (listForums.size() != 0) {
				return new ResponseEntity<List<Forum>>(listForums, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Forum>>(listForums, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception ex) {
			return new ResponseEntity<List<Forum>>(listForums, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	// ------------------Update Forum -----------------------------------

		@PutMapping(value = "/updateForum/{forumId}")
		public ResponseEntity<String> updateForum(@PathVariable("forumId") int forumId, @RequestBody Forum forum) {
			System.out.println("Updating Forum " + forumId);
			Forum uforum = forumDao.getForum(forumId);
			if (uforum == null) {
				System.out.println("Forum with forumId " + forumId + " Not Found");
				return new ResponseEntity<String>("Update Forum Failue", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			uforum.setForumContent(forum.getForumContent());
			uforum.setForumName(forum.getForumName());
			uforum.setCreateDate(new Date());
			uforum.setStatus(forum.getStatus());
			uforum.setUserName(forum.getUserName());
			forumDao.updateForum(uforum);
			return new ResponseEntity<String>("Update Forum Success", HttpStatus.OK);
		}

		// -----------------------Get Forum ------------------------------------

		@GetMapping(value = "/getForum/{forumId}")
		public ResponseEntity<Forum> getForum(@PathVariable("forumId") int forumId) {
			System.out.println("Get Forum " + forumId);
			Forum forum = forumDao.getForum(forumId);
			if (forum == null) {
				System.out.println("Forum retrieval failure..");
				return new ResponseEntity<Forum>(forum, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				System.out.println("<==========Forum retrieved========>");
				return new ResponseEntity<Forum>(forum, HttpStatus.OK);
			}
		}

		// -----------------------Approve Forum ----------------------------------

		@PutMapping(value = "/approveForum/{forumId}")
		public ResponseEntity<String> approveForum(@PathVariable("forumId") int forumId) {
			System.out.println("Approve Forum with Forum ID: " + forumId);
			Forum forum = forumDao.getForum(forumId);
			if (forum == null) {
				System.out.println("Not forum with forum Id: " + forumId + " found for Approval");
				return new ResponseEntity<String>("No Forum found for Approval", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				forum.setStatus("A");
				forumDao.approveForum(forum);
				return new ResponseEntity<String>("Forum " + forumId + " Approved Successfully", HttpStatus.OK);
			}
		}

		// --------------------Reject Forum ----------------------------------

		@PutMapping(value = "/rejectForum/{forumId}")
		public ResponseEntity<String> rejectForum(@PathVariable("forumId") int forumId) {
			System.out.println("Reject Forum with Forum ID: " + forumId);
			Forum forum = forumDao.getForum(forumId);
			if (forum == null) {
				System.out.println("Not forum with forum Id: " + forumId + " found for Approval");
				return new ResponseEntity<String>("No Forum with Forum Id " + forumId + " found for Disapproval",
						HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				forum.setStatus("NA");
				forumDao.rejectForum(forum);
				return new ResponseEntity<String>("Forum " + forumId + " Disapproved Successfully", HttpStatus.OK);
			}
		}

		// -------------------------Delete Forum ---------------------

		@DeleteMapping(value = "/deleteForum/{forumId}")
		public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId) {
			System.out.println("Delete forum with forum Id: " + forumId);
			Forum forum = forumDao.getForum(forumId);
			if (forum == null) {
				System.out.println("No forum " + forumId + " found to delete");
				return new ResponseEntity<String>("No forum with forum Id: " + forumId + " found to delete",
						HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				forumDao.deleteForum(forum);
				return new ResponseEntity<String>("Forum with Forum Id " + forumId + " deleted successfully", HttpStatus.OK);
			}

		}
		// ---------------- Add ForumComments -----------------------------------

				@PostMapping(value = "/addForumComment")
				public ResponseEntity<String> addForumComments(@RequestBody ForumComment forumComment) {
					forumComment.setDiscussionDate(new Date());
					Forum forum = forumDao.getForum(1);
					String username = forum.getUserName();
					int forumId = forum.getForumId();
					forumComment.setForumId(forumId);
					forumComment.setUserName(username);
					if (forumDao.addForumDiscussion(forumComment)) {
						return new ResponseEntity<String>("ForumComment Added- Success", HttpStatus.OK);
					} else {
						return new ResponseEntity<String>("ForumComment insert failed", HttpStatus.NOT_FOUND);
					}
				}

				// -------------------------Delete ForumComment	 ---------------------

				@DeleteMapping(value = "/deleteForumComment/{commentId}")
				public ResponseEntity<String> deleteForumComment(@PathVariable("commentId") int commentId) {
					System.out.println("Delete forumComment with comment Id: " + commentId);
					ForumComment forumComment = forumDao.getForumDiscussion(commentId);
					if (forumComment == null) {
						System.out.println("No forum " + commentId + " found to delete");
						return new ResponseEntity<String>("No forumcomment with comment Id: " + commentId + " found to delete",
								HttpStatus.NOT_FOUND);
					} else {
						forumDao.deleteForumDiscussion(forumComment);
						return new ResponseEntity<String>("ForumComment with comment Id " + commentId + " deleted successfully", HttpStatus.OK);
					}

				}
				// -----------------------Get ForumComment ------------------------------------

				@GetMapping(value = "/getForumComment/{commentId}")
				public ResponseEntity<ForumComment> getForumComment(@PathVariable("commentId") int commentId) {
					System.out.println("Get ForumComment " + commentId);
					ForumComment forumComment = forumDao.getForumDiscussion(commentId);
					if (forumComment == null) {
						return new ResponseEntity<ForumComment>(forumComment, HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
					}
				}

				// -----------------list Forums ---------------------------------
				@GetMapping(value = "/listForumComments")
				public ResponseEntity<List<ForumComment>> listForumComments() {
					List<ForumComment> listForumComments = forumDao.listForumDiscussion(1);
					if (listForumComments.size() != 0) {
						return new ResponseEntity<List<ForumComment>>(listForumComments, HttpStatus.OK);
					} else {
						return new ResponseEntity<List<ForumComment>>(listForumComments, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
}
