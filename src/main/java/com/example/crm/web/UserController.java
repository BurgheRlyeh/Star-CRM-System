package com.example.crm.web;

import com.example.crm.entities.User;
import com.example.crm.entities.Users;
import com.example.crm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Аннотация @RestController предписывает Spring распознать
 * данный класс как бин контроллера Spring MVC
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    /**
     * Аннотация @GetMapping - аналог @RequestMapping с методом получения GET
     * @return List of all users in DB
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/list")
    public Users listUsers() {
        return new Users(userService.findAll());
    }

    @GetMapping(value ="/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value="/")
    public User create(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody User user, @PathVariable Long id) {
        userService.save(user);
    }

    @Autowired
    public void setUserService(UserService service) {
        this.userService = service;
    }
}
