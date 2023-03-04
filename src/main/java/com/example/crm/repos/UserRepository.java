package com.example.crm.repos;

import com.example.crm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
