package com.example.curddemo.dao;

import com.example.curddemo.entity.Course;
import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;
import com.example.curddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);

     Instructor findInstructorById(int id);

     void deleteInstructorById(int id);

     InstructorDetail findInstructorDetailById(int theId);

     void deleteInstructorDetailById(int id);

     List<Course> findCoursesByInstructorId(int Id);

     Instructor findInstructorByIdJoinFetch(int Id);

     void update(Instructor tempInstructor);

     void update(Course tempCourse);

     Course findCourseById(int id);

     void deleteCourseById(int id);

     void save(Course theCourse);

     Course findCourseAndReviewByCourseId(int theId);

     Course findCourseAndStudentsByCourseId(int theId);

     Student findStudentAndCoursesByStudentId(int theId);

     void update(Student theStudent);

     void deleteStudentById(int theId);

}
