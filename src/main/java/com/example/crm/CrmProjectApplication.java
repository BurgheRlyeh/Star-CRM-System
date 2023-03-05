package com.example.crm;

import com.example.crm.entities.Course;
import com.example.crm.entities.PaymentStatus;
import com.example.crm.entities.User;
import com.example.crm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class CrmProjectApplication {

    private static Logger logger = LoggerFactory.getLogger(CrmProjectApplication.class);

    public static void main(String... args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(CrmProjectApplication.class, args);
        assert (ctx != null);
        logger.info("Application started...");

        //Пример работы с сервисом:
        UserService service = ctx.getBean(UserService.class);
        listAllUsers(service.findByName("Коля"));

        System.in.read();
        ctx.close();
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

