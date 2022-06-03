package com.example.student.management.database;

import com.example.student.management.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBase {
    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {}
        };
    }

}
