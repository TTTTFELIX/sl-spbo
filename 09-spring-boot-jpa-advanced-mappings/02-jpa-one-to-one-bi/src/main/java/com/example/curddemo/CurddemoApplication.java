package com.example.curddemo;

import com.example.curddemo.dao.AppDAO;
import com.example.curddemo.entity.Instructor;
import com.example.curddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};
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
