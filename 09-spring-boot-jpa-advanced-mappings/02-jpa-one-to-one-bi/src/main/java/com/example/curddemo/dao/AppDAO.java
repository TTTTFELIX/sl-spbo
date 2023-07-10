package com.example.curddemo.dao;

import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor instructor);

     Instructor findInstructorById(int id);

     void deleteInstructorById(int id);

     InstructorDetail findInstructorDetailById(int theId);

     void deleteInstructorDetailById(int id);

}
