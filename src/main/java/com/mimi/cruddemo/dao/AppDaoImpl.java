package com.mimi.cruddemo.dao;

import com.mimi.cruddemo.entity.Course;
import com.mimi.cruddemo.entity.Instructor;
import com.mimi.cruddemo.entity.InstuctorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl  implements AppDao{
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist((theInstructor));
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return  entityManager.find(Instructor.class, theId);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor tempInstructor =entityManager.find(Instructor.class, theId);
        // get the courses
        List<Course> courses = tempInstructor.getCourses();
        // break association of all courses for the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }
        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstuctorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstuctorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstuctorDetail tempInstDet =entityManager.find(InstuctorDetail.class, theId);
        // remove the associated object refrence
        // break bi-directional link
        tempInstDet.getInstructor().setInstuctorDetail(null);
        // delete instructor detail
        entityManager.remove(tempInstDet);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query for finding courses by instructor id
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id= :data ",Course.class );
        // set the parameter of the query
        query.setParameter("data", theId);
        // execute the query
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        //Instructor refers to the entity class,
        // not the name of the table in your database.
        TypedQuery<Instructor> query= entityManager.createQuery(
                "select i from Instructor i "+
                        "JOIN FETCH i.courses "+
                        "JOIN FETCH i.instuctorDetail "+
                        "where i.id=:data",Instructor.class
        );
        // setting the parameters
        query.setParameter("data", theId);
        // execute query
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
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);

    }

    @Override
    public Course fincdCourseById(int theId) {
        return entityManager.find(Course.class, theId);

    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);
        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id=:data",
                Course.class
        );
        query.setParameter("data", theId);
        Course course=query.getSingleResult();
        return course ;
    }


}
