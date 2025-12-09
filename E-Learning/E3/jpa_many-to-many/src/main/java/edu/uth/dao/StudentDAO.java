package edu.uth.dao;

import java.util.List;

import edu.uth.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDAO {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public StudentDAO(String persistanceName) {
        emf = Persistence.createEntityManagerFactory(persistanceName);
    }

    public void save(Student student) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("Save success!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Student> getStudents() {
        List<Student> students = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            students = em.createQuery("from Student").getResultList();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            em.close();
        }
        return students;
    }

    public Student findById(int studentID) {
        Student student = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            student = em.find(Student.class, studentID);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            em.close();
        }
        return student;
    }

    public void delete(int studentID) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student s = em.find(Student.class, studentID);
            em.remove(s);
            em.getTransaction().commit();
            System.out.println("Delete success!");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void update(Student student) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student s = em.find(Student.class, student.getId());
            if (s != null) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setMarks(student.getMarks());
                em.getTransaction().commit();
                System.out.println("Update success!");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
