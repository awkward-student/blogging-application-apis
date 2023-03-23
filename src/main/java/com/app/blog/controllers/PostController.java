package com.app.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.blog.config.AppConstants;
import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.PostDTO;
import com.app.blog.payloads.PostResponse;
import com.app.blog.services.FileService;
import com.app.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	// get posts by user
	@RequestMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDTO> postDTOs = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	// get posts by category
	@RequestMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDTO> postDTOs = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	// get all posts
	@RequestMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// get post by id
	@RequestMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		PostDTO postDTO = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted!", true);
	}

	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable Integer postId) {
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	// search post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostDTO> postDTOs = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}

	// post image upload
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {
		PostDTO postDTO = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDTO.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	// method to serve files
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
