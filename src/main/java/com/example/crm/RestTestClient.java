package com.example.crm;

import com.example.crm.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Данный тестовый класс клиента выполняет различные примитивные
 * операции к базе данных.
 * TODO: перенести все тесты в отдельный пакет для тестирования
 * средствами JUnit
 */
public class RestTestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestTestClient.class);
    private static final String URL_GET_ALL_USERS = "http://localhost:8080/user/list";
    private static final String URL_GET_USER_BY_ID = "http://localhost:8080/user/{id}";
    private static final String URL_DELETE_USER_BY_ID = "http://localhost:8080/user/{id}";
    private static final String URL_CREATE_USER = "http://localhost:8080/user/";
    private static final String URL_UPDATE_USER = "http://localhost:8080/user/{id}";
    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing findAll:");
        Users users = restTemplate.getForObject(URL_GET_ALL_USERS, Users.class);
        listAllUsers(users.getUsers());
    }

    @Test
    public void testFindById() {
        logger.info("--> Testing findById:");
        User user = restTemplate.getForObject(URL_GET_USER_BY_ID, User.class, 4);
        logger.info(user.toString());
        Assert.assertEquals(user.getName(), "Коля");
    }

    @Test
    public void testDeleteById() {
        logger.info("--> Testing deleteById:");
        restTemplate.delete(URL_DELETE_USER_BY_ID, 5);
    }

    @Test
    public void testCreate() {
        logger.info("--> Testing create user");
        User newUser = new User();
        newUser.setName("BB");
        newUser.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        ActiveStatus activeStatus = new ActiveStatus();
        activeStatus.setStatus("test_status");
        newUser.setActiveStatus(activeStatus);
        newUser = restTemplate.postForObject(URL_CREATE_USER, newUser, User.class);
        logger.info("Singer created successfully: " + newUser);
    }

    @Test
    public void testUpdate() {
        logger.info("--> Testing update user");
        User user = restTemplate.getForObject(URL_GET_USER_BY_ID, User.class, 1);
        user.setName("Миша Иванов");

        restTemplate.put(URL_UPDATE_USER, user, 1);
        logger.info("User updated successfully");
    }

    /**
     * Список всех учеников со всеми связями. Для тех, кто будет тестировать через JUnit:
     * можно использовать данный метод в тестах, каждая сущность содержит полноценное
     * описание в toString().
     *
     * @param scholars
     */
    public static void listAllUsers(List<User> scholars) {
        logger.info(" ---- Listing all scholars:");
        for (User scholar : scholars) {
            logger.info(scholar.toString());
            if (scholar.getParent() != null) {
                logger.info("\t" + scholar.getParent().toString());
            }

            if (scholar.getActiveStatus() != null) {
                logger.info("\t" + scholar.getActiveStatus().toString());
            }

            if (scholar.getPaymentStatus() != null) {
                logger.info("\t" + scholar.getActiveStatus().toString());
            }

            if (scholar.getCourses() != null) {
                for (Course course : scholar.getCourses()) {
                    logger.info("\t" + course.toString());
                }
            }
        }
    }
}
