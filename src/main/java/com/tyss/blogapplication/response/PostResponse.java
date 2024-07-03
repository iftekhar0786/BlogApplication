package com.tyss.blogapplication.response;

import java.util.List;

import com.tyss.blogapplication.payloads.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;

	private Integer pageNo;

	private Integer pageSize;

	private Long totalElements;

	private Integer totalPages;

	private Boolean lastPage;
}
