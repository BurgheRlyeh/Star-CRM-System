package com.example.crm.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "parents")
public class Parent implements Serializable {
    private Long id;
    private String name;
    private String phone;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Parent name: " + name + ", " +
                "Phone: " + phone;
    }
}
