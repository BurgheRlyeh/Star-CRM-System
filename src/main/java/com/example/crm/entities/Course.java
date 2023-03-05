package com.example.crm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    private Long id;
    private String course;
    //private Set<User> users = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "COURSE")
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "users_courses", joinColumns = @JoinColumn(name = "COURSE_ID"),
    //        inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    //public Set<User> getUsers() {
    //    return users;
    //}

    //public void setUsers(Set<User> users) {
     //   this.users = users;
    //}

    @Override
    public String toString() {
        return "Course name: " + course;
    }
}
