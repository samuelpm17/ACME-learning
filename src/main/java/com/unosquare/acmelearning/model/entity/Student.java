package com.unosquare.acmelearning.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "person_id")
public class Student extends Person {

	@Column(name = "parent_phone")
	private String parentPhone;
	
	@Column(name = "parent_name")
	private String parentName;

	private static final long serialVersionUID = 5130047022946615547L;

}
