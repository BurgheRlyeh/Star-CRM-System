package com.example.crm.web;

import com.example.crm.entities.User;
import com.example.crm.entities.Users;
import com.example.crm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/crmstarsystem/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public Users test() {
        return new Users(userService.findAll());
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('READ')")
    public Users listAllUsers() {
        return new Users(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(value = "/list", params = {"page", "size"})
    @PreAuthorize("hasAuthority('READ')")
    public List<User> listUsersPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<User> users = userService.findAll(page, size);

        if (page > users.getTotalPages()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to found page " + page);
        }
        return users.getContent();
    }

    @GetMapping(value = "/list", params = {"page", "size", "attribute"})
    @PreAuthorize("hasAuthority('READ')")
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
    @PreAuthorize("hasAuthority('WRITE')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('WRITE')")
    public User create(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public void update(@RequestBody User user, @PathVariable Long id) {
        userService.save(user);
    }

}
