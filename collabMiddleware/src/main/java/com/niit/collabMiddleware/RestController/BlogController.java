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

import com.niit.collabBackend.Dao.BlogDao;
import com.niit.collabBackend.model.Blog;

@RestController
public class BlogController {
	
	@Autowired
	BlogDao blogDao;
	
	// --------------------------------- List Blog  ---------------------------\\
	

		@GetMapping(value="/listBlog")
		public ResponseEntity<List<Blog>> getListBlog(){
			System.out.println("List of Blog");
			List<Blog> list = blogDao.listBlog("akiv khan");
			if(list.size()>0)
				return new ResponseEntity<List<Blog>>(list,HttpStatus.OK);
			else
				return new ResponseEntity<List<Blog>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	//------------------------- ADD BLOG ------------------------\\
	@PostMapping(value="/addblog")
	public ResponseEntity<String> addBlog (@RequestBody Blog blog){
		System.out.println("Adding blog...........!!!");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setUserName("akiv khan");
		blog.setStatus("A");
		
		if(blogDao.addBlog(blog)) {
			return new ResponseEntity<String> ("Success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		//--------------------- Update Blog--------------------\\
		@PutMapping(value = "/updateBlog/{blogId}")
		public ResponseEntity<String> updateBlog(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
			System.out.println("Updating Blog " + blogId);
			System.out.println("Updating Blog " + blog.getBlogId());
			Blog mBlog = blogDao.getBlog(blogId);
			if (mBlog == null) {
				System.out.println("Blog with blogId " + blogId + " Not Found");
				return new ResponseEntity<String>("Update Blog Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			mBlog.setBlogContent(blog.getBlogContent());
			mBlog.setBlogName(blog.getBlogName());
			mBlog.setCreateDate(new Date());
			mBlog.setLikes(mBlog.getLikes());
			mBlog.setStatus(mBlog.getStatus());
			mBlog.setUserName(mBlog.getUserName());
			
			blogDao.updateBlog(mBlog);
			return new ResponseEntity<String>("Update Blog Success", HttpStatus.OK);
			
		}
		//------------------Get BLog-----------------------------------\\
		
		@GetMapping(value = "/getBlog/{blogId}")
		public ResponseEntity<String> getBlog(@PathVariable("blogId") int blogId) {
			System.out.println("Get Blog " + blogId);
			Blog blog = blogDao.getBlog(blogId);
			if (blog == null) {
				return new ResponseEntity<String>("Get blog Error", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Get blog Success", HttpStatus.OK);
			}
		}
		
		// -----------------------Approve Blog ----------------------------------\\

				@PutMapping(value = "/approveBlog/{blogId}")
				public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId) {
					System.out.println("Approve Blog with Blog ID: " + blogId);
					Blog blog = blogDao.getBlog(blogId);
					if (blog == null) {
						System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
						return new ResponseEntity<String>("No Blog found for Approval", HttpStatus.INTERNAL_SERVER_ERROR);
					} else {
						blog.setStatus("A");
						blogDao.approveBlog(blog);
						return new ResponseEntity<String>("Blog " + blogId + " Approved Successfully", HttpStatus.OK);
					}
				}
				// --------------------Reject Blog ---------------------------------------\\

				@PutMapping(value = "/disapproveBlog/{blogId}")
				public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId) {
					System.out.println("Disapprove Blog with Blog ID: " + blogId);
					Blog blog = blogDao.getBlog(blogId);
					if (blog == null) {
						System.out.println("Not blog with blog Id: " + blogId + " found for Approval");
						return new ResponseEntity<String>("No Blog with Blog Id " + blogId + " found for Disapproval",
								HttpStatus.INTERNAL_SERVER_ERROR);
					} else {
						blog.setStatus("NA");
						blogDao.rejectBlog(blog);
						return new ResponseEntity<String>("Blog " + blogId + " Disapproved Successfully", HttpStatus.OK);
					}
				}
		
		//-------------------- Delete Blog-----------------------------------
				
		@DeleteMapping(value = "/deleteBlog/{blogId}")
		public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
			System.out.println("Delete blog with blog Id: " + blogId);
			Blog blog = blogDao.getBlog(blogId);
			if (blog == null) {
				System.out.println("No blog " + blogId + " found to delete");
				return new ResponseEntity<String>("No blog with blog Id: " + blogId + " found to delete",
						HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				blogDao.deleteBlog(blog);
				return new ResponseEntity<String>("Blog with Blog Id " + blogId + " deleted successfully", HttpStatus.OK);
			}
			
			}
			
	/*		//---------------- Add BlogComment-------------
			@PostMapping(value = "/addBlogComment")
			public ResponseEntity<String> addBlogComments(@RequestBody BlogComment blogComment) {
				blogComment.setCommentDate(new Date());
				Blog blog = blogDao.getBlog(1);
				String username = blog.getUserName();
				int blogId = blog.getBlogId();
				blogComment.setBlogId(blogId);
				blogComment.setUserName(username);
				if (blogDao.addBlogComment(blogComment)) {
					return new ResponseEntity<String>("BlogComment Added- Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("BlodComment insert failed", HttpStatus.NOT_FOUND);
				}
			}

			// -------------------------Delete BlogComment	 ---------------------

			@DeleteMapping(value = "/deleteBlogComment/{commentId}")
			public ResponseEntity<String> deleteBlogComment(@PathVariable("commentId") int commentId) {
				System.out.println("Delete blogComment with comment Id: " + commentId);
				BlogComment blogComment = blogDao.getBlogComment(commentId);
				if (blogComment == null) {
					System.out.println("No blog " + commentId + " found to delete");
					return new ResponseEntity<String>("No blogcomment with comment Id: " + commentId + " found to delete",
							HttpStatus.NOT_FOUND);
				} else {
					blogDao.deleteBlogComment(blogComment);
					return new ResponseEntity<String>("BlogComment with comment Id " + commentId + " deleted successfully", HttpStatus.OK);
				}

			}
			// -----------------------Get BlogComment ------------------------------------

			@GetMapping(value = "/getBlogComment/{commentId}")
			public ResponseEntity<String> getBlogComment(@PathVariable("commentId") int commentId) {
				System.out.println("Get BlogComment " + commentId);
				BlogComment blogComment = blogDao.getBlogComment(commentId);
				if (blogComment == null) {
					return new ResponseEntity<String>("Get blogComment failure", HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity<String>("Get blogComment Success", HttpStatus.OK);
				}
			}

			// -----------------list Blogs ---------------------------------
			@GetMapping(value = "/listBlogComments")
			public ResponseEntity<List<BlogComment>> listBlogComments() {
				List<BlogComment> listBlogComments = blogDao.listBlogComments(1);
				if (listBlogComments.size() != 0) {
					return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.NOT_FOUND);
				}
			}
			
			
			
			// -----------------increment likes ---------------------------------
			@PostMapping(value = "/incrementLikes/{blogID}")
			public ResponseEntity<String> incrementLikes(@PathVariable("blogId")int blogId) {
				System.out.println("Increment likes for BlogId " + blogId);
				Blog blog = blogDao.getBlog(blogId);
				if (blogDao.incrementLike(blog)) {
					return new ResponseEntity<String>("Successfully incremented..", HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Failed incremeneting likes..", HttpStatus.NOT_FOUND);
				}
}
			*/
	}


