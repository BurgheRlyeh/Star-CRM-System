package com.example.crm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "payment_statuses")
public class PaymentStatus implements Serializable {
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


    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "users_courses", joinColumns = @JoinColumn(name = "PAYMENT_STATUS"),
    //        inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    //public Set<User> getUsers() {
    //    return users;
    //}

    //public void setUsers(Set<User> users) {
    //    this.users = users;
    //}

    @Override
    public String toString() {
        return "Payment status: " + status;
    }
}
