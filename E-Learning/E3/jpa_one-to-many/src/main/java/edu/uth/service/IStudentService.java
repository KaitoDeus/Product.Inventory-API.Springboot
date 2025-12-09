package edu.uth.service;

import java.util.List;

import edu.uth.pojo.Student;

public interface IStudentService {
    public List<Student> findAll();
    public void save(Student student);
    public void delete(int studentID);
    public Student findById(int studentID);
    public void update(Student student); 
}
