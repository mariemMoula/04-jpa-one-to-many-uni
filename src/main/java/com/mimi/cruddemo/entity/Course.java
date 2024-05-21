package com.mimi.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
//Each course (represented by an instance of the Course entity) is associated with exactly one instructor
// (represented by an instance of the Instructor entity).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;

    @Column(name = "title")
    private String title ;
//Multiple instances of the Course entity can be linked to
// a single instance of the Instructor entity.
    @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE,
                        CascadeType.DETACH , CascadeType.REFRESH} )
    @JoinColumn(name = "instructor_id")
    private Instructor instructor ;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")//to tell hibernate how to associate yhe reviews with the given course,it means that
    // the column named course_id in the review , will point back to the actual course
    private List<Review> reviews ;

    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    // add a convenience method
    public void addReview(Review theReview){
        if(reviews==null){
          reviews =new ArrayList<>();
        }
        reviews.add(theReview);
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}



//Unidirectional Relationship (Course-Review):
//
//In the Course entity, you use @OneToMany to indicate that one course can have many reviews. Additionally, you use @JoinColumn to specify the name of the foreign key column (course_id) in the review table that maps back to the id column in the course table.
//In the Review entity, you don't need a field representing the foreign key (course_id) because the relationship is unidirectional. The Review entity only knows that it is associated with a specific course but doesn't have a direct reference to the course.
//Bidirectional Relationship (Instructor-Course):
//
//In the Instructor entity, you use @OneToMany to indicate that one instructor can teach many courses. You also use the mappedBy attribute to specify the field (instructor) in the Course entity that owns the relationship. This means that the relationship is bidirectional, and the mapping information is provided in the Course entity.
//In the Course entity, you use @ManyToOne to specify that each course is associated with one instructor. You also use @JoinColumn to specify the name of the foreign key column (instructor_id) in the course table that maps back to the id column in the instructor table.