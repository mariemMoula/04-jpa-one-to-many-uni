package com.mimi.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="instructor_detail") // should haave the same name as the table in the dh
public class InstuctorDetail {
    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db columns names

    // create constructors

    // generate getters/setters methods

    // generate toString method
    // mapped by refers to instructorDetail property in Instructor class
    // THIS MEANS THAT IT WILL CASCADE ALL OPERATIONS BUT THE REMOVE OPERATION
    @OneToOne(mappedBy = "instuctorDetail",cascade = {CascadeType.DETACH ,CascadeType.MERGE ,CascadeType.PERSIST , CascadeType.REFRESH  })
    private Instructor instructor ;



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name ="id")
    private int id ;
    @Column(name ="youtube_channel")
    private String youtubeChannel ;
    @Column(name ="hobby")
    private String hobby ;

    public InstuctorDetail() {
    }

    public InstuctorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstuctorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
