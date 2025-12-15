package com.example.demo.controller;

import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmRepository filmRepository;

    public FilmController(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFilm(@PathVariable Long id, @RequestBody Film filmDetails) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        if (optionalFilm.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        filmRepository.save(filmDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        if (!filmRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        filmRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

