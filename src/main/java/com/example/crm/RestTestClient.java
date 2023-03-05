package com.example.crm;

import com.example.crm.entities.Course;
import com.example.crm.entities.PaymentStatus;
import com.example.crm.entities.User;
import com.example.crm.entities.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTestClient {
    private static final Logger logger = LoggerFactory.getLogger(RestTestClient.class);
    private static final String URL_GET_ALL_USERS = "http://localhost:8080/user/list";
    private static final String URL_GET_USER_BY_NAME = "http://localhost:8080/user/{name}";

    private RestTemplate restTemplate;
    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("user",
                "0a145717-5011-4e48-a9ce-067893d0f828"));
    }

    @Test
    public void testFindAll() {
        logger.info("--> Testing findAll:");
        Users users = restTemplate.getForObject(URL_GET_ALL_USERS, Users.class);
        listAllUsers(users.getUsers());
    }

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

            if (scholar.getPaymentStatuses() != null) {
                for (PaymentStatus status : scholar.getPaymentStatuses()) {
                    logger.info("\t" + status.toString());
                }
            }

            if (scholar.getCourses() != null) {
                for (Course course : scholar.getCourses()) {
                    logger.info("\t" + course.toString());
                }
            }
        }
    }
}
