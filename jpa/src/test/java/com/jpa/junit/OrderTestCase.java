package com.jpa.junit;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.onetomany.bean.Order;
import com.jpa.onetomany.bean.OrderItem;

public class OrderTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Order order = new Order();
		order.setAmount(199f);
		//order.setOrderId(UUID.randomUUID().toString());
		order.setOrderId("1000");
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProduceName("邮票");
		orderItem.setSalePrice(99f);
		order.addOrderItem(orderItem);
		
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setProduceName("篮球");
		orderItem1.setSalePrice(100f);
		order.addOrderItem(orderItem1);
		
		em.persist(order);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
