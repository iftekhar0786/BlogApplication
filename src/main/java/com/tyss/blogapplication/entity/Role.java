//package com.tyss.blogapplication.entity;
//
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Data
//@Entity
//public class Role {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	private String name;
//	
//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//	private Set<User> users;
//
//}
