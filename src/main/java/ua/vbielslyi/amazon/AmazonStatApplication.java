package ua.vbielslyi.amazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
@EnableCaching
public class AmazonStatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonStatApplication.class, args);
    }

}
