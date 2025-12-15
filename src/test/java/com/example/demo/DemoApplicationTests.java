package com.example.demo;

import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    FilmRepository filmRepository;

    @Test
    void contextLoads() {
        log.info(filmRepository.findAll().toString());
        filmRepository.save(
                Film.builder()
                        .genre("truc")
                        .nom("truc film")
                        .build()
        );
        filmRepository.flush();

    }

}
