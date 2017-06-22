package com.jpa.onetomany.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 40, nullable = false)
	private String produceName;
	private Float salePrice = 0f;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
	//,fetch=FetchType.EAGER 默认就为立即加载 因为为one
	//optional=true 表示关联字段可以为空 ， optional=false 表示这个对象必须存在,反映在数据库中就是必须存在不能为空
	@JoinColumn(name="order_id")//设定外键名称
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderItem() {
	}

	public OrderItem(String produceName, Float salePrice) {
		this.produceName = produceName;
		this.salePrice = salePrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public Float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
}
