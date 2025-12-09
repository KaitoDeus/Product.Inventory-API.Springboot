package edu.uth;

import java.util.Scanner;

import edu.uth.dao.StudentDAO;
import edu.uth.pojo.Student;

public class Main {
    public static void main(String[] args) {
        System.out.println("++++++++++MENU++++++++++");
        System.out.println("+ 1. Create Student +");
        System.out.println("+ 2. Delete Student +");
        System.out.println("+ 3. Update Student +");
        System.out.println("+ 4. Find Student by ID +");
        System.out.println("+ 0. Quit +");
        System.out.println("++++++++++END++++++++++");

        int inputKey = -1;
        while (inputKey != 0) {
            Scanner console = new Scanner(System.in);
            System.out.println("Please enter a number !");
            inputKey = console.nextInt();
            StudentDAO studentDAO = new StudentDAO("JPAs");
            Student student = new Student("Lam", "Nguyen", 9);
            switch (inputKey) {
                case 0:
                    break;
                case 1:
                    studentDAO.save(student);
                    break;
                case 2:
                    studentDAO.delete(1);
                    break;
                case 3:
                    student = new Student(1, "Sang", "Nguyen", 9);
                    studentDAO.update(student);
                case 4:
                    System.out.println(studentDAO.findById(1));
                    break;
                default:
                    System.out.println("Please choice menu !");
            }
        }
    }
}