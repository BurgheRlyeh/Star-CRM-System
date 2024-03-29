package com.example.crm.repos;

import com.example.crm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findAll(Pageable pageable);
}
