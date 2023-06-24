package com.example.curddemo.dao;

import com.example.curddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

//    define field for entity manager
    private EntityManager entityManager;

//    inject entity manager by using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

//    implement save method
    @Override
    @Transactional
    public void save(Student theStudent){
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
//        create query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student", Student.class);

//        return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
//        created the query
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "from Student where lastName=:theData", Student.class);

//        set the parameter
        typedQuery.setParameter("theData", theLastName);

//        return the result
        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
//        retrieve the student
        Student student = entityManager.find(Student.class, id);
//        delete the student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numOfRowsDelete = entityManager.createQuery("DELETE from Student").executeUpdate();

        return numOfRowsDelete;
    }


}
