package com.example.curddemo;

import com.example.curddemo.dao.AppDAO;
import com.example.curddemo.entity.Course;
import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;
import com.example.curddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@SpringBootApplication
public class CurddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {

//			createCourseAndReview(appDAO);
//			retriveCourseAndReview(appDAO);
			deleteCourseAndReview(appDAO);


		};
	}

	private void deleteCourseAndReview(AppDAO appDAO) {

		int id = 10;

		System.out.println("Delete course by id: " + id);

		appDAO.deleteCourseById(id);

		System.out.println("Done!");

	}

	private void retriveCourseAndReview(AppDAO appDAO) {

//		get the course and reviews
		int id = 10;
		Course course = appDAO.findCourseAndReviewByCourseId(id);

//		print the course
		System.out.println(course);

//		print the review
		System.out.println(course.getReviews());

		System.out.println("Done!");

	}

	private void createCourseAndReview(AppDAO appDAO) {

//		Create new course
		Course course = new Course("KIN - Basketball");

//		add reviews
		course.addReview(new Review("Great"));
		course.addReview(new Review("Good"));
		course.addReview(new Review("Sucks"));

//		save the course
		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());

		appDAO.save(course);

		System.out.println("Done!");

	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Delete the course by id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done");

	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Find the course by id: " + theId);

		Course course = appDAO.findCourseById(theId);

		System.out.println("Update the course by id : " + theId);

		course.setTitle("Network");

		appDAO.update(course);

		System.out.println("Done!");

	}

	private void updateInstrudtor(AppDAO appDAO) {

		int id = 1;

		System.out.println("Find the instructor by id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Update the instructor id: " + id);

		instructor.setFirstName("Mic");

		appDAO.update(instructor);

		System.out.println("Done!");


	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int id = 1;

		System.out.println("Finding the instructor by id: " + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("TempInstructor: " + instructor);
		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {

		int id = 1;

		System.out.println("Finding the instructor id: " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("TempInstructor: " + instructor);

//		find courses
		System.out.println("Finding the courses for instructorId: " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);

		instructor.setCourses(courses);

		System.out.println("The associate courses: " + instructor.getCourses());
		System.out.println("Done!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Find instructor by id: " + theId);
		System.out.println("The associated courses: " + instructor.getCourses());

		System.out.println("Done!");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//		create the instructor
		Instructor tempInstructor = new Instructor("Tom", "Nie", "tom@gmail.com");

//		create the instructorDetail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/tom","Coding");

//		associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

//		create some courses
		Course course1 = new Course("CS - Computer Network");
		Course course2 = new Course("CS - Database");

//		add courses to instructor
		tempInstructor.add(course1);
		tempInstructor.add(course2);

//		save the instructor
//
//		Note: this is also save the course
//		Because of CascadeType.PERSIST
//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId=3;

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");

	}

	private void findInstructorDetail(AppDAO appDAO) {

//		get the instructor detail object
		int theId = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(theId);

//		print the instructorDetail object
		System.out.println("TempInstructorDetail: " + instructorDetail);

//		print the associated instructor
		System.out.println("the associated instructor: " + instructorDetail.getInstructor());

		System.out.println("Done!");

	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Delete the instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");

	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding the instructor id: " + theId);

		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + instructor);
		System.out.println("the associated instructorDetail only: " + instructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

//		create the instructor
		Instructor tempInstructor = new Instructor("Tom", "Nie", "tom@gmail.com");

//		create the instructorDetail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.tom.com/youtube","Coding");

//		associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

//		save the instructor
//
//		Note: this will also save the detail object
//		Because of the CascadeType.ALL
//
		System.out.println("Saving Instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");

	}

}
