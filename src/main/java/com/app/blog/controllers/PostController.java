package com.app.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.PostDTO;
import com.app.blog.services.PostService;



@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;

	//	create	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	//	get posts by user
	@RequestMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDTO> postDTOs = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
	//	get posts by category
	@RequestMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDTO> postDTOs = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
	//	get all posts
	@RequestMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> postDTOs = this.postService.getAllPosts();
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
	
	//	get post by id
	@RequestMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDTO = this.postService.getPostById(postId);
		return  new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}
	
	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted!", true);
	}
	
	//	update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable Integer postId){
		PostDTO updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
}
