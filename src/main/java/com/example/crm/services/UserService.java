package com.example.crm.services;

import com.example.crm.entities.Course;
import com.example.crm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Данный сервис позволяет выполнять различные операции
 * Пока - получение по ID и получение всех учеников, получение по имени.
 *
 * TODO:
 * реализовать постраничное отображение с помощью интерфейса Pageable
 */
public interface UserService {
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
    User save(User user);
    Page<User> findByName(String name, int page, int size);
    Page<User> findAll(int page, int size);
}