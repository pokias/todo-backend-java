package com.todo.todobackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    private static final Logger log = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(NoteRepository repository) {
        return args -> {
            log.info("testing " + repository.save(new NoteEntity("long note", 123L)));
            log.info("testing " + repository.save(new NoteEntity("long note 2", 124L)));
        };
    }
}
