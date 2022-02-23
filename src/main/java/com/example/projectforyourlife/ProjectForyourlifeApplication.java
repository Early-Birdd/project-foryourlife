package com.example.projectforyourlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectForyourlifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectForyourlifeApplication.class, args);
    }

}
