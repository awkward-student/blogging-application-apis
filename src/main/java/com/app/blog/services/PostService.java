package com.app.blog.services;

import java.util.List;

//import com.app.blog.entities.Post;
import com.app.blog.payloads.PostDTO;
import com.app.blog.payloads.PostResponse;

public interface PostService {
	//create
	PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);
	//update
	PostDTO updatePost(PostDTO postDto, Integer postId);
	//delete
	void deletePost(Integer postId);
	//get single post
	PostDTO getPostById(Integer postId);
	//get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	//get all posts by category
	List<PostDTO> getPostsByCategory(Integer categoryId);
	//get all posts by user
	List<PostDTO> getPostsByUser(Integer userId);
	//search posts
	List<PostDTO> searchPosts(String keyword);
}
