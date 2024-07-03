package com.tyss.blogapplication.service;

import java.util.List;

import com.tyss.blogapplication.payloads.PostDto;
import com.tyss.blogapplication.response.PostResponse;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get single post
	PostDto getPost(Integer postId);
	
	//get All post
	PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir);
	
	//get All post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post by keyword
	List<PostDto> searchPosts(String keyword);
	
}
