package com.app.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.blog.entities.Comment;
import com.app.blog.entities.Post;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CommentDTO;
import com.app.blog.repos.CommentRepo;
import com.app.blog.repos.PostRepo;
import com.app.blog.services.CommentService;

public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id: ", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub

	}

}
