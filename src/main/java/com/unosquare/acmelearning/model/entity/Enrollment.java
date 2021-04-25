package com.unosquare.acmelearning.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "enrollment_date", nullable = false)
	@DateTimeFormat
	private Date enrollmentDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "student_id")
	private Student student;

	private static final long serialVersionUID = -4679113473385348486L;
	
}
