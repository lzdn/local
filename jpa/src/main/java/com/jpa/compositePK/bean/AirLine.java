package com.jpa.compositePK.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class AirLine {

	@EmbeddedId // 表示 AirLinePK 作为 AirLine 的一个属性
	private AirLinePK id;
	@Column(length = 20)
	private String name;

	public AirLine() {
	}

	public AirLine(String startCity, String endCity, String name) {
		this.id = new AirLinePK(startCity, endCity);
		this.name = name;
	}

	public AirLinePK getId() {
		return id;
	}

	public void setId(AirLinePK id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
