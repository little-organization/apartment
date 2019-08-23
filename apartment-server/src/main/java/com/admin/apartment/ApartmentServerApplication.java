package com.admin.apartment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class ApartmentServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentServerApplication.class, args);
    }

}
