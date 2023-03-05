package com.example.crm.web;

import com.example.crm.entities.User;
import com.example.crm.entities.Users;
import com.example.crm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public List<User> findByName(String name) {
        return userService.findByName(name);
    }

    @Autowired
    public void setUserService(UserService service) {
        this.userService = service;
    }
}
