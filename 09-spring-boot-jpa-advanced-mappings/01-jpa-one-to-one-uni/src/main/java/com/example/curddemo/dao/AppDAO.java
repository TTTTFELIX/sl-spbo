package com.example.curddemo.dao;

import com.example.curddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor instructor);

     Instructor findInstructorById(int id);

     void deleteInstructorById(int id);

}
