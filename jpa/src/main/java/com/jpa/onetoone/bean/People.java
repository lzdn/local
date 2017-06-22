package com.jpa.onetoone.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class People {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=10,nullable=false)
	private String name;

	@OneToOne(optional=false,cascade={CascadeType.ALL})
	@JoinColumn(name="idCard_id")
	private IDCard idCard;
	
	public People() {
 
	}
	
	public People(String name) {
		this.name = name;
	}

	public IDCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IDCard idCard) {
		this.idCard = idCard;
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
