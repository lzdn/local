package com.jpa.manytomany.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Student {

	@Id
	@GeneratedValue
	private Integer Id;
	@Column(length = 10, nullable = false)
	private String name;

	@ManyToMany(cascade = { CascadeType.REFRESH })
	@JoinTable(name = "student_teacher", inverseJoinColumns = @JoinColumn(name = "teacher_id"), joinColumns = @JoinColumn(name = "student_id"))
	private Set<Teacher> teachers = new HashSet<Teacher>();

	public void addTeacher(Teacher teacher) {
		this.teachers.add(teacher);
	}

	public void removeTeacher(Teacher teacher) {
		if(this.teachers.contains(teacher)){
			this.teachers.remove(teacher);
		}
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
