package com.tyss.blogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.blogapplication.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
