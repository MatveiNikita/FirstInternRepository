package com.example.firstinternrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoAuditing
public class FirstInternRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstInternRepositoryApplication.class, args);
    }

}
