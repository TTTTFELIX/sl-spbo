package com.example.curddemo.dao;

import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImpl implements AppDAO{

//    define field the entity manager
    private EntityManager entityManager;

//    inject entity manager using constructor inject
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {

//        retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);

//        delete the instructor
        entityManager.remove(instructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        //        retrieve the instructorDetail
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

//        remove the associated object reference
//        break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);

//        delete the instructorDetail
        entityManager.remove(instructorDetail);

    }
}
