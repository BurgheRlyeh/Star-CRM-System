package com.example.crm.services;

import com.example.crm.entities.User;
import java.util.List;

/**
 * Данный сервис позволяет выполнять различные операции
 * Пока - получение по ID и получение всех учеников, получение по имени.
 */
public interface UserService {
    User findById(Long id);
    List<User> findAll();
    List<User> findByName(String name);
}