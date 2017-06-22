package com.jpa.junit;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.bean.Person;

public class PersonTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	// http://www.cnblogs.com/007sx/p/5658194.html
	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");//创建元数据 /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person("张林");
		person.setBirthday(new Date());
		em.persist(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void get() {// 相当于hibernate get() 方法 该方法会查不到会返回null
		// 读取数据时不用开启事物
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		Person person = em.find(Person.class, 1);
		System.out.println(person.toString());
		em.close();
		factory.close();
	}

	@Test
	public void load() {// 相当于hibernate load() 方法
						// 该方法只有第一次在使用该对象时才会真正的装载数据，如果查不到数据就会报错
		// 读取数据时不用开启事物
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		Person person = em.getReference(Person.class, 1);
		System.out.println(person.toString());
		em.close();
		factory.close();
	}

	@Test
	public void update() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		person.setName("孙悟空");
		person.setBirthday(new Date());
		em.getTransaction().commit();
		em.close();
		factory.close();
		// 思考为什么会重新创建了一条数据？
		// 思考为什么会重新创建了一条数据？
		// hibernate 状态：new(新建) 托管 游离 删除
		// new Person()
		// 托管 实体在托管状态时（实体与hibernate session 事物 存在联系），我们修改实体的属性会在最后同步到数据库

	}

	@Test
	public void update2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		em.clear();// 把实体管理器中的所有实体变成游离状态
		person.setName("牛魔王");
		em.merge(person);// 把游离状态的实体同步到数据库
		person.setBirthday(new Date());
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void delete() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		em.remove(person);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test // JPQL查询语句
	public void query() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		// em.createQuery("select p from Person p where p.id=?");位参
		// em.createQuery("select p from Person p where p.id=:id");命名参数
		Query query = em.createQuery("select p from Person p where p.id=?1");// 参数索引1
		query.setParameter(1, 1);
		List<Person> Persons = query.getResultList();
		for (Person person : Persons) {
			System.out.println(person.toString());
		}
		// Person person = (Person)query.getSingleResult();//单结果不存在会报错

		// System.out.println(person.toString());
		em.close();
		factory.close();
	}

	@Test
	public void deleteQuery() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("delete from Person p where p.id=?1");// 参数索引1
		query.setParameter(1, 2);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void updateQuery() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("update Person p set p.name=:name where p.id=:id");// 参数索引1
		query.setParameter("name", "首发得分");
		query.setParameter("id", 3);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
