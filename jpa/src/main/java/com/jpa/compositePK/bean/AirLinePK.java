package com.jpa.compositePK.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable //嵌入 表示这个对象嵌入某个对象作为其一个属性
public class AirLinePK implements Serializable{

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8653183826022408066L;
	@Column(length=3)
	private String startCity;
	@Column(length=3)
	private String endCity;

	public AirLinePK() {
	}

	public AirLinePK(String startCity, String endCity) {
		this.startCity = startCity;
		this.endCity = endCity;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
		result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirLinePK other = (AirLinePK) obj;
		if (endCity == null) {
			if (other.endCity != null)
				return false;
		} else if (!endCity.equals(other.endCity))
			return false;
		if (startCity == null) {
			if (other.startCity != null)
				return false;
		} else if (!startCity.equals(other.startCity))
			return false;
		return true;
	}

}
