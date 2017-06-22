package com.jpa.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
// @Table(name="PersonTable")
public class Person {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 20, nullable = false, name = "personName")
	private String name;

	@Temporal(TemporalType.DATE)
	private Date birthday;// 2017-01-01

	@Enumerated(EnumType.STRING) // EnumType.ORDINAL 枚举的索引值 这里保存字符串
	@Column(length = 5, nullable = false) // 保存枚举时不能为空
	private Gender gender = Gender.MAN;// 为数据库设置默认值
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Lob
	private String info ;//大文本数据保存，因为varchar最大255
	
	@Lob
	@Basic(fetch=FetchType.LAZY)//文件太大 延迟加载 /懒加载
	private Byte[] file;//存放二进制数据

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}
	@Transient//此注解表示该字段不作为数据库映射字段,默认是会映射为数据库的字段
	private String imagePath;

	public String getInfo() {
		return info;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
