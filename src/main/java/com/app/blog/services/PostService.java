package com.app.blog.services;

import java.util.List;

import com.app.blog.entities.Post;
import com.app.blog.payloads.PostDTO;

public interface PostService {
	//create
	Post createPost(PostDTO postDto, Integer userId, Integer categoryId);
	//update
	Post updatePost(PostDTO postDto, Integer postId);
	//delete
	void deletePost(Integer postId);
	//get single post
	Post getPostById(Integer postId);
	//get all posts
	List<Post> getAllPosts();
	//get all posts by category
	List<Post> getPostsByCategory(Integer categoryId);
	//get all posts by user
	List<Post> getPostsByUser(Integer userId);
	//search posts
	List<Post> searchPosts(String keyword);
}
