package com.admin.apartment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class ApartmentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentServerApplication.class, args);
    }

}
