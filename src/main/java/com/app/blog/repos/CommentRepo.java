package com.app.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
