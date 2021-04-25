package com.unosquare.acmelearning.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "instructors")
@PrimaryKeyJoinColumn(name = "person_id")
public class Instructor extends Person {

	private Long salary;
	@OneToMany
	private Set<Course> courses;

	private static final long serialVersionUID = -6378586931253369795L;

}
