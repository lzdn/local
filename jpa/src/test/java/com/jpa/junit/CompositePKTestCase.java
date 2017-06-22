package com.jpa.junit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.compositePK.bean.AirLine;

public class CompositePKTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(new AirLine("PEK", "SHA", "北京飞上海"));

		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
