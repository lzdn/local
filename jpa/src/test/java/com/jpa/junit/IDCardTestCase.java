package com.jpa.junit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.onetoone.bean.IDCard;
import com.jpa.onetoone.bean.People;

public class IDCardTestCase {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}
	@Test
	public void save(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");//创建元数据 /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		People people = new People("小刘");
		IDCard idcard = new IDCard("111111");
		//people.setName("小刘");
		people.setIdCard(idcard);
		em.persist(people);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
