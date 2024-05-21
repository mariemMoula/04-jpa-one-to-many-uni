package com.mimi.cruddemo.dao;


import com.mimi.cruddemo.entity.Course;
import com.mimi.cruddemo.entity.Instructor;
import com.mimi.cruddemo.entity.InstuctorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstuctorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);
    void updateCourse(Course tempCourse);
    Course fincdCourseById(int theId);

    void deleteCourseById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);

}
