package com.gu;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MySpringApplication {


    public static void main( String[] args )
    {
        org.springframework.boot.SpringApplication.run(MySpringApplication.class);
    }

}
