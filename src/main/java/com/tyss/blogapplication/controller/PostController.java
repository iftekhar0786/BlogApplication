package com.tyss.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.blogapplication.appconstant.AppConstant;
import com.tyss.blogapplication.payloads.PostDto;
import com.tyss.blogapplication.response.PostResponse;
import com.tyss.blogapplication.response.Response;
import com.tyss.blogapplication.service.PostService;

//@CrossOrigin(originPatterns = "http://localhost:8080")
@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/savePost/{categoryId}/{userId}")
	public ResponseEntity<Response> createPost(@RequestBody PostDto postDto, @PathVariable Integer categoryId,
			@PathVariable Integer userId) {
		PostDto post = postService.createPost(postDto, userId, userId);
		return new ResponseEntity<Response>(new Response(false, "post saved successfully", post), HttpStatus.CREATED);
	}

	// get post by user

	@GetMapping("/getPostByUser/{userId}")
	public ResponseEntity<Response> getPostByUser(@PathVariable Integer userId) {
		List<PostDto> postByUser = postService.getPostByUser(userId);
		return new ResponseEntity<Response>(new Response(false, "fetched successfully", postByUser), HttpStatus.OK);

	}

	// get post by category
	@GetMapping("/getPostByCategory/{categoryId}")
	public ResponseEntity<Response> getPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postByUser = postService.getPostByUser(categoryId);
		return new ResponseEntity<Response>(new Response(false, "fetched successfully", postByUser), HttpStatus.OK);

	}

	// get post by id
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<Response> getPostById(@PathVariable Integer postId) {
		PostDto postDto = postService.getPost(postId);
		return new ResponseEntity<Response>(new Response(false, "fetched successfully", postDto), HttpStatus.OK);

	}

	// get All post
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstant.PAGE_NO, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir

	) {
		PostResponse allPost = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);

		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

	}

	// delete post by id
	@DeleteMapping("deletePost/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>("post deleted successfully", HttpStatus.OK);
	}

	// update post
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<Response> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = postService.updatePost(postDto, postId);

		return new ResponseEntity<Response>(new Response(false, "updated successfully", updatePost), HttpStatus.OK);
	}

	// search post
	@GetMapping("/searchPost/{keyword}")
	public ResponseEntity<Response> searchPost(@PathVariable("keyword") String title) {
		List<PostDto> posts = this.postService.searchPosts(title);
		return new ResponseEntity<Response>(new Response(false, "found succesfully", posts), HttpStatus.OK);

	}

}
