package com.example.curddemo;

import com.example.curddemo.dao.StudentDAO;
import com.example.curddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner (StudentDAO studentDAO){
        return runner -> {
//            createStudentDAO(studentDAO);

            createMultiStudentDAO(studentDAO);

//            readStudent(studentDAO);

//            queryForStudents(studentDAO);

//            queryForStudentsByLastName(studentDAO);

//            updateStudent(studentDAO);

//            deleteStudent(studentDAO);

//            deleteAll(studentDAO);

        };

    }

    private void deleteAll(StudentDAO studentDAO) {
        System.out.println("Deleting all.....");

        int deletedRows = studentDAO.deleteAll();

        System.out.println("Delete rows count: " + deletedRows);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 2;

        System.out.println("Delete student id: " + studentId);

        studentDAO.delete(studentId);

    }

    private void updateStudent(StudentDAO studentDAO) {
//        retrieved student based on id: primary key
        int studentId = 1;
        System.out.println("Getting student id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

//        change the first name to "Scooby"
        System.out.println("Updating the student.....");
        myStudent.setLastName("Doe");

//        update the student
        studentDAO.update(myStudent);

//        display the updated student
        System.out.println("Student updated: " + myStudent);

    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
//        get a list of students
        List<Student> students = studentDAO.findByLastName("Doe");

//        display that list of the students
        for (Student student: students){
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
//        get a list of students
        List<Student> theStudent = studentDAO.findAll();
//        display the list of students
        for (Student student: theStudent){
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
//        create student object
        System.out.println("Create new student object......");
        Student tempStudent = new Student("Tom","Nie", "tom@gmail.com");

//        save the student
        System.out.println("Saving the student......");
        studentDAO.save(tempStudent);

//        display the student
        int studentId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + studentId);

//        retrieve the student
        System.out.println("Retrieve student with id: " + studentId);
        Student student = studentDAO.findById(studentId);

//        found the student
        System.out.println("Founded student: " + student);
    }

    private void createMultiStudentDAO(StudentDAO studentDAO) {
//        create 3 student objects
        System.out.println("Creating 3 student objects......");
        Student tempStudent1 = new Student("John", "Doe", "John@gmail.com");
        Student tempStudent2 = new Student("Mic", "Doe", "Mic@gmail.com");
        Student tempStudent3 = new Student("Tom", "Doe", "Tom@gmail.com");
//        save objects
        System.out.println("Saving the 3 student objects......");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }

    private void createStudentDAO(StudentDAO studentDAO) {
//        create student object
        System.out.println("Creating new student object......");
        Student tempStudent = new Student("Paul", "Doe", "Paul@gmail.com");

//        save the student object
        System.out.println("Saving the student......");
        studentDAO.save(tempStudent);

//        display id of the saved student
        System.out.println("Saved student id is: " + tempStudent.getId());
    }


}
