package com.example.crm.web;

import com.example.crm.entities.User;
import com.example.crm.entities.Users;
import com.example.crm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

/**
 * Аннотация @RestController предписывает Spring распознать
 * данный класс как бин контроллера Spring MVC.
 * С помощью аннотации @RequestMapping указываем адрес контроллера,
 * по которому он будет доступен клиенту (нашему фронту).
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;


    @GetMapping(path = "/test_principal_name")
    @PreAuthorize("hasAuthority('READ')")
    public String test(Principal principal) {
        return principal.getName();
    }

    /**
     * С помощью аннотации @GetMapping указывается адрес данного ресурса, а также параметры
     * @return List of all users in DB
     */
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('READ')")
    public /*@ResponseBody*/ Users listUsers() {
        return new Users(userService.findAll());
    }

    /**
     * Аннотация @PathVariable преобразовывает атрибут id типа String URL-запроса в Long
     * @param id
     * @return User
     */
    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/list", params = {"page", "size"})
    public List<User> listUsersPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<User> users = userService.findAll(page, size);

        if (page > users.getTotalPages()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);
        }
        return users.getContent();
    }

    @GetMapping(value = "/list", params = {"page", "size", "attribute"})
    public List<User> listUsersSorted(@RequestParam("page") int page, @RequestParam("size") int size,
                                      @RequestParam("attribute") String attribute) {
        Page<User> users = userService.findAll(page, size, attribute);

        if (page > users.getTotalPages()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);
        }

        return users.getContent();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
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
