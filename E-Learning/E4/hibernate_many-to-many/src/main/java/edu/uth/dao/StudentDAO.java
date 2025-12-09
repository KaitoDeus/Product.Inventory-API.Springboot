package edu.uth.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.uth.pojo.Student;

public class StudentDAO {
    private SessionFactory sessionFactory = null;
    private Configuration cf = null;

    public StudentDAO(String persistanceName) {
        cf = new Configuration();
        cf = cf.configure(persistanceName);
        sessionFactory = cf.buildSessionFactory();
    }

    public void save(Student student) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.persist(student);
            t.commit();
            System.out.println("successfully saved");
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            sessionFactory.close();
            session.close();
        }
    }

    public List<Student> getStudents() {
        Session session = sessionFactory.openSession();
        List<Student> students = null;

        try {
            session.beginTransaction();
            students = session.createQuery("from Student", Student.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }

        return students;
    }

    public Student findById(int studentID) {
        Session session = sessionFactory.openSession();
        try {
            return (Student) session.get(Student.class, studentID);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    public void delete(int studentID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Student student = (Student) session.get(Student.class, studentID);
            session.remove(student);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.merge(student);
            t.commit();
            System.out.println("update saved");
        } catch (Exception e) {
            t.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            sessionFactory.close();
            session.close();
        }
    }
}
