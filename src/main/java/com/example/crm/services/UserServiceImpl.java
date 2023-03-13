package com.example.crm.services;

import com.example.crm.entities.User;
import com.example.crm.repos.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализация сервиса обслуживания для операций с базой данных.
 * Данную реализацию можно использовать в контроллере. Данную
 * реализацию можно расширять (дополняя интерфейс UserService).
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findByName(String name, int page, int size) {
        return userRepository.findByName(name, PageRequest.of(page, size));
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Autowired
    public void setSingerRepository(UserRepository courseRepository) {
        this.userRepository = courseRepository;
    }
}

