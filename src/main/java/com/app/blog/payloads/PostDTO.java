package com.app.blog.payloads;

import java.util.Date;

import com.app.blog.entities.Category;
import com.app.blog.entities.User;

public class PostDTO {
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private Category category;
	private User user;
}
