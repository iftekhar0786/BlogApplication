package com.tyss.blogapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	
	@Column(name = "title")
	private String categoryTitle;
	
	
	@Column(name = "discription")
	private String categoryDiscription;
	
	
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	private List<Post> posts;
	
}
