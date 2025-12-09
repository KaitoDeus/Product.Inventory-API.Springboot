package edu.uth;

import edu.uth.pojo.Book;
import edu.uth.pojo.Student;
import edu.uth.service.IStudentService;
import edu.uth.service.StudentService;

public class Main {
    public static void main(String[] args) {
        
        // Demo Many to Many
        IStudentService studentService = new StudentService("hibernate.cfg.xml");
        Student student = new Student("Lam", "Nguyen", 9);
        Book book = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
        student.getBooks().add(book);
        studentService.save(student);
    }
}