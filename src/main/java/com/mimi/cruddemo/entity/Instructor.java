package com.mimi.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="instructor")
public class Instructor {
    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db columns names

    // ** set up mapping to InstructorDetail entity

    // create constructors

    // generate getters/setters methods

    // generate toString method
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id")
    private int id ;
    @Column(name = "first_name")
    private String firstName ;
    @Column(name = "last_name")
    private String LastName ;
    @Column(name = "email")
    private String email ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstuctorDetail instuctorDetail ;

    @OneToMany(mappedBy = "instructor",cascade ={CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH , CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    private List<Course> courses ;
    public Instructor() {
    }

    public Instructor( String firstName, String lastName, String email) {
        this.firstName = firstName;
        LastName = lastName;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public InstuctorDetail getInstuctorDetail() {
        return instuctorDetail;
    }

    public void setInstuctorDetail(InstuctorDetail instuctorDetail) {
        this.instuctorDetail = instuctorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", instuctorDetail=" + instuctorDetail +
                '}';
    }

    //add convinience methods for bi-directional relationships
    public void add(Course theCourse){
        if (courses==null){
            courses=new ArrayList<>();
        }
        courses.add(theCourse);
        theCourse.setInstructor(this);
    }


}
