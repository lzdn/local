package com.jpa.junit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.manytomany.bean.Student;
import com.jpa.manytomany.bean.Teacher;

public class ManyToManyTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Student("小明"));
		em.persist(new Teacher("张老师"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void buildTSRelate() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		student.addTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void deleteTSRelate() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		student.removeTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void deleteTeacher() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Student student = em.find(Student.class, 1);
		Teacher teacher = em.getReference(Teacher.class, 1);
		student.removeTeacher(teacher);// 先解除关系
		em.remove(teacher);// 再执行删除
		em.getTransaction().commit();
		em.close();
		factory.close();

	}

	@Test
	public void deleteStudent() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("lzdn");// 创建元数据
																						// /表
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		em.remove(student);
		em.getTransaction().commit();
		em.close();
		factory.close();

	}
}
