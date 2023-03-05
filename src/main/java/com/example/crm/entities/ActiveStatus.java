package com.example.crm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "active_statuses")
public class ActiveStatus implements Serializable {
    private Long id;
    private String status;
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

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //@OneToMany(mappedBy = "activeStatus", cascade = CascadeType.ALL)
    //public Set<User> getUsers() {
    //    return users;
    //}

    //public void setUsers(Set<User> users) {
    //    this.users = users;
    //}

    @Override
    public String toString() {
        return "Active status: " + status;
    }
}
