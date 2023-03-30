package com.app.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.app.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private Integer postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<Comment> comments = new HashSet<>();
}
