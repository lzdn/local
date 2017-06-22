package com.jpa.onetoone.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class IDCard {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=18,nullable=false)
	private String cardno;

	@OneToOne(mappedBy="idCard",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	private People people;
	
	
	public IDCard(String cardno) {
		this.cardno = cardno;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
}
