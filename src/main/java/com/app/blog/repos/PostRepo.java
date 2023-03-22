package com.app.blog.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.Category;
import com.app.blog.entities.Post;
import com.app.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
//	searching - manual methods
	List<Post> findByTitleContaining(String title);
}
