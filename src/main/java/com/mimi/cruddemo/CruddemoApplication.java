package com.mimi.cruddemo;

import com.mimi.cruddemo.dao.AppDao;
import com.mimi.cruddemo.entity.Course;
import com.mimi.cruddemo.entity.Instructor;
import com.mimi.cruddemo.entity.InstuctorDetail;
import com.mimi.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return args -> {
//			createInstructor(appDao);
//			findInstructor(appDao);
//			deleteInstructor(appDao);
//			findInstructorDetailById(appDao);
//			deleteInstructorDetailById(appDao);
//			createInstructorWithCourses(appDao);
//			findInstructorWithCourses(appDao);
//			findCoursesForInstructor(appDao);
//			findInstructorByIdWithJoinFetch(appDao);
//			updateInstructor(appDao);
//			updateCourse(appDao);
//			deleteCourse(appDao);
//           createCourseAndReviews(appDao);
//            retrieveCourseAndReviews(appDao);
            deletCourseAndReviews(appDao);
        };

    }

    private void deletCourseAndReviews(AppDao appDao) {
        int theId=10;
        // will delete the reviews as well bcz oof the cascade type all
        appDao.deleteCourseById(theId);

    }

    private void retrieveCourseAndReviews(AppDao appDao) {
        int theId = 10;
        Course temlpCourse= appDao.findCourseAndReviewsByCourseId(theId);
        System.out.println(temlpCourse);
        System.out.println("REVIEWS:"+temlpCourse.getReviews());

    }

    private void createCourseAndReviews(AppDao appDao) {
        // create a course
        Course tempCourse = new Course("How to stay healthy");
        // add some reviews on that course
        tempCourse.addReview(new Review("Great Course"));
        tempCourse.addReview(new Review("Needed this Course...the best"));
        tempCourse.addReview(new Review("That was really stupid"));
        // save the course .. and leverage the cascade all
        System.out.println("Saving the course ");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        appDao.save(tempCourse);
        System.out.println("Done");

    }

    private void deleteCourse(AppDao appDao) {
        int theId = 12;
        System.out.println("/***************************/");
        System.out.println("DELETING  COURSE WITH ID :" + theId);
        System.out.println("/***************************/");
        appDao.deleteCourseById(theId);
        System.out.println("Done !");
    }

    private void updateCourse(AppDao appDao) {
        int theId = 12;
        System.out.println("Updating the course with id:" + theId);
        System.out.println("/***************************/");
        System.out.println("FINDING THE COURSE");
        Course tempCourse = appDao.fincdCourseById(theId);
        System.out.println("the original course is " + tempCourse);
        tempCourse.setTitle("Loves to fuck");
        appDao.updateCourse(tempCourse);
        System.out.println("/***************************/");
        System.out.println("the updated version of the course" + tempCourse);


    }

    private void updateInstructor(AppDao appDao) {
        int theId = 1;
        // find the Instructor
        System.out.println("Finfing the instructor with this id:" + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);
        // update the instructor
        System.out.println("Updating instructor witg id:" + theId);
        tempInstructor.setLastName("Updated");
        appDao.update(tempInstructor);
        System.out.println("The updated version is : " + tempInstructor);
    }

    private void findInstructorByIdWithJoinFetch(AppDao appDao) {
        int theId = 1;
        System.out.println("Finding the instructor by id: " + theId + " ...");
        Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);
        System.out.println("/***************************/");
        System.out.println(tempInstructor);
        System.out.println("/***************************/");
        System.out.println("the associated courses:" + tempInstructor.getCourses());
        System.out.println("/***************************/");
    }

    private void findCoursesForInstructor(AppDao appDao) {
        int theId = 1;
        System.out.println("Finding the instructor by id: " + theId + " ...");
        Instructor tempInstructor = appDao.findInstructorById(theId);
        System.out.println("/***************************/");
        System.out.println(tempInstructor);
        System.out.println("/***************************/");
        System.out.println("The associated instructor  detail  only is : " + tempInstructor.getInstuctorDetail());
        System.out.println("/***************************/");
        // we need to retrieve the courses ourselves since the loading is lazy
        List<Course> courses = appDao.findCoursesByInstructorId(theId);
        tempInstructor.setCourses(courses);
        System.out.println("/***************************/");
        System.out.println("The associated courses: " + tempInstructor.getCourses());
        System.out.println("/***************************/");
        System.out.println("DONE");


    }

    private void findInstructorWithCourses(AppDao appDao) {

        int theId = 1;
        System.out.println("Finding the instructor by id: " + theId + " ...");
        Instructor tempInstructor = appDao.findInstructorById(theId);
        System.out.println("/***************************/");
        System.out.println(tempInstructor);
        System.out.println("/***************************/");
        System.out.println("The associated instructor  detail  only is : " + tempInstructor.getInstuctorDetail());
        System.out.println("/***************************/");
        // will not load courses since the fetch type is LAZY
        System.out.println("The associated courses: " + tempInstructor.getCourses());
        System.out.println("DONE");

    }

    private void createInstructorWithCourses(AppDao appDao) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Mimi", "Lil", "mimi@gmail.com");
        // create the instructor detail
        InstuctorDetail tempInstructorDetail = new InstuctorDetail("lilyoutube.com", "loveToFuck");
        // associate the object
        tempInstructor.setInstuctorDetail(tempInstructorDetail);
        // save the instructor , and so the instructor detail will be saved
        // create some courses , since every instructor can have many courses
        Course tempCourse1 = new Course("Spring boot Course");
        Course tempCourse2 = new Course("Django Course");
        Course tempCourse3 = new Course("Angular Course");
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);
        tempInstructor.add(tempCourse3);
        System.out.println("/***************************/");
        System.out.println("SAVING THE INSTRUCTOR....");
        System.out.println("/***************************/");
        System.out.println("The courses we re saving : " + tempInstructor.getCourses());
        appDao.save(tempInstructor);
        System.out.println("INSTRUCTOR SAVED....");
        // the courses will be saved as well beacuse of the cascade type PERSIST
        System.out.println("/***************************/");


    }

    private void deleteInstructorDetailById(AppDao appDao) {
        int theId = 3;
        System.out.println("/***************************/");
        System.out.println("Deleting the Instructor detail and its associated Instructor... ");
        System.out.println("/***************************/");
        appDao.deleteInstructorDetailById(theId);
        System.out.println("/***************************/");
        System.out.println("Done");
        System.out.println("/***************************/");
    }

    private void findInstructorDetailById(AppDao appDao) {
        int theId = 2;
        System.out.println("Finding the instructorDetail by id: " + theId + " ...");
        InstuctorDetail temInstructorDetail = appDao.findInstructorDetailById(theId);
        System.out.println("/***************************/");
        System.out.println("The InstructorDetail is : " + temInstructorDetail);
        System.out.println("/***************************/");
        System.out.println("the associated instructor is : " + temInstructorDetail.getInstructor());
        System.out.println("/***************************/");

    }

    private void deleteInstructor(AppDao appDao) {
        int theId = 1;
        System.out.println("/***************************/");
        System.out.println("Deleting instructor id: " + theId);
        System.out.println("/***************************/");
        appDao.deleteInstructorById(theId);
        System.out.println("Done");
        System.out.println("/***************************/");
    }

    private void findInstructor(AppDao appDao) {
        int theId = 2;
        System.out.println("Finding the instructor by id: " + theId + " ...");
        Instructor tempInstructor = appDao.findInstructorById(theId);
        System.out.println("/***************************/");
        System.out.println(tempInstructor);
        System.out.println("/***************************/");
        System.out.println("The associated instructor  detail  only is : " + tempInstructor.getInstuctorDetail());
        System.out.println("/***************************/");


    }

    private void createInstructor(AppDao appDao) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@gmail.com");
        // create the instructor detail
        InstuctorDetail tempInstructorDetail = new InstuctorDetail("youtube.com", "loveToCode");
        // associate the object
        tempInstructor.setInstuctorDetail(tempInstructorDetail);
        // save the instructor , and so the instructor detail will be saved
        System.out.println("/***************************/");
        System.out.println("SAVING THE INSTRUCTOR....");
        System.out.println("/***************************/");
        appDao.save(tempInstructor);
        System.out.println("INSTRUCTOR SAVED....");
        System.out.println("/***************************/");
        // create the instructor
//		Instructor tempInstructor = new Instructor("Susan","Lopez","Lopez@gmail.com");
//		// create the instructor detail
//		InstuctorDetail tempInstructorDetail = new InstuctorDetail("youtube1.com","loveToDance");
//		// associate the object
//		tempInstructor.setInstuctorDetail(tempInstructorDetail);
//		// save the instructor , and so the instructor detail will be saved
//		System.out.println("/***************************/");
//		System.out.println("SAVING THE INSTRUCTOR....");
//		System.out.println("/***************************/");
//		appDao.save(tempInstructor);
//		System.out.println("INSTRUCTOR SAVED....");
//		System.out.println("/***************************/");
    }
}
