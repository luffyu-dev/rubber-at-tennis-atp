package com.rubber.at.tennis.atp.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.rubber.*")
@MapperScan("com.rubber.at.tennis.**.dao.mapper")
@SpringBootApplication
public class RubberAtTennisAtpWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RubberAtTennisAtpWebApplication.class, args);
    }

}
