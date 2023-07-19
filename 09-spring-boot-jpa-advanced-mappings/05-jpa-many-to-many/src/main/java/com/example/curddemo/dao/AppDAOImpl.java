package com.example.curddemo.dao;

import com.example.curddemo.entity.Course;
import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;
import com.example.curddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

//        find course
        List<Course> courses = instructor.getCourses();


//        break associate of all the courses for the instructor
        for (Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }


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

    @Override
    public List<Course> findCoursesByInstructorId(int Id) {

//        create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);

        query.setParameter("data", Id);

        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int Id) {

        //        create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i Join FETCH i.courses where i.id = :data", Instructor.class);

        query.setParameter("data", Id);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);

    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int theId) {

//        create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c join fetch c.reviews where c.id = :data", Course.class);

        query.setParameter("data", theId);

//        execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
//        create the query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "join fetch c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

//        execute the query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

//        create the query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "+
                        "join fetch s.courses "+
                        "where s.id = :data", Student.class);

        query.setParameter("data", theId);

//        execute the query
        Student student = query.getSingleResult();

        return student;

    }

    @Override
    @Transactional
    public void update(Student theStudent) {

        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

//        retrieve the student
        Student student = entityManager.find(Student.class,theId);

//        delete the student
        entityManager.remove(student);

    }
}
