package ru.cool.amlapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmlappApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmlappApplication.class, args);
        System.out.println("hello");
    }

}
