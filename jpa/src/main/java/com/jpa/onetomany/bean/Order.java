package com.jpa.onetomany.bean;

import java.util.HashSet;
import java.util.Set;

//import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(length = 12, nullable = false)
	private String orderId;
	@Column(nullable = false)
	private Float amount = 0f;

	//定义 订单与订单项的关系 和级联操作
	//这里的级联操作 只有调用hibernate 的 REFRESH PERSIST MERGE REMOVE 方法时才会起作用 才会触发
	//REFRESH 的介绍：当我们从数据库中获取到数据后，没有立马使用，而是稍后使用。例如，业务处理花了几分钟。
	//这几分钟可能有人修改了这条数据库的数据，REFRESH 就是重新获取最新的数据，
	//而如果使用find（）方法再查是获取不到最新的数据，因为他会在 EntityManager一级缓存中查找这个对象。
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},mappedBy="order")
	//mappedBy="order" order 为OrderItem中的属性 表示谁被维护
	//@Basic(fetch=FetchType.LAZY)//@OneToMany 碰到后面是Many 默认就是延迟加载 ，得到多的一方。延迟加载 必须保证EntityManager是打开状态
	private Set<OrderItem> items = new HashSet<OrderItem>();  
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	public void addOrderItem(OrderItem orderItem){
		orderItem.setOrder(this);
		this.items.add(orderItem);
	}

}
