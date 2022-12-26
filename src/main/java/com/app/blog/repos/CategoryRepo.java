package com.app.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
