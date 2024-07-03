package com.tyss.blogapplication.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tyss.blogapplication.entity.Category;
import com.tyss.blogapplication.entity.Post;
import com.tyss.blogapplication.entity.User;
import com.tyss.blogapplication.exception.CategoryNotFoundException;
import com.tyss.blogapplication.exception.PostNotFoundException;
import com.tyss.blogapplication.exception.UserNotFoundException;
import com.tyss.blogapplication.payloads.PostDto;
import com.tyss.blogapplication.repository.CategoryRepository;
import com.tyss.blogapplication.repository.PostRepository;
import com.tyss.blogapplication.repository.UserRepository;
import com.tyss.blogapplication.response.PostResponse;
import com.tyss.blogapplication.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		Category category = categoryRepository.findById(userId)
				.orElseThrow(() -> new CategoryNotFoundException("categori not found"));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		postRepository.save(post);
		return postDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post not found"));

		// updating only these three
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(updatedPost, PostDto.class);

		return postDto2;
	}

	@Override
	public void deletePost(Integer postId) {
		postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post not found"));

		postRepository.deleteById(postId);

	}

	@Override
	public PostDto getPost(Integer postId) {

		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post not found"));

		PostDto postDto = this.modelMapper.map(post, PostDto.class);

		return postDto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

		/*
		 * by if-else Sort sort=null; if(sortDir.equalsIgnoreCase("asc")) { sort =
		 * Sort.by(sortBy).ascending(); }else { sort = Sort.by(sortBy).descending(); }
		 */

		// by ternary operator
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		PageRequest page = PageRequest.of(pageNo, pageSize, sort); // Sort.by(sortBy).descending()->to sort in
																	// descending order
		Page<Post> pagePost = postRepository.findAll(page);

		if (pagePost.isEmpty()) {
			throw new PostNotFoundException("category not found");
		}
		List<Post> allPostts = pagePost.getContent();

		if (allPostts.isEmpty()) {
			throw new PostNotFoundException("post not found");
		}

		List<PostDto> postDto = allPostts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		// setting data to postResponse
		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDto);
		postResponse.setPageNo(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new CategoryNotFoundException("category not found"));

		List<Post> posts = postRepository.findByCategory(category);
		if (posts.isEmpty()) {
			throw new PostNotFoundException("category not found");
		}
		return posts.stream().map((post) ->

		this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		List<Post> Users = postRepository.findByUser(user);

		if (Users.isEmpty()) {
			throw new UserNotFoundException("user not found");
		}
		return Users.stream().map((usr) ->

		this.modelMapper.map(usr, PostDto.class)).collect(Collectors.toList());

	}

	@Override
	public List<PostDto> searchPosts(String title) {
		List<Post> posts = this.postRepository.findByKeyword(title);
		
	
		List<PostDto> list = new ArrayList<PostDto>();
		posts.stream().forEach((post)->{
			PostDto postDto = new PostDto();
			BeanUtils.copyProperties(post, postDto);
			list.add(postDto);
		});

		return list;
		//note:-> category and user still remaining to convert
	}

}
