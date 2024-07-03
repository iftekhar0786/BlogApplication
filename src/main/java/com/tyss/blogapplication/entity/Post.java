package com.tyss.blogapplication.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;

	@Column(name = "post_title", length = 200, nullable = false)
	private String title;

	@Column(name = "post_content", length = 10000)
	private String content;

	private String imageName;

	private Date addedDate;

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_Id")
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

}
