package com.todo.todobackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private final NoteRepository repository;

    NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    List<NoteEntity> all() {
        return repository.findAll();
    }

    @GetMapping("/poster/{id}")
    List<NoteEntity> allByPoster(@PathVariable Long id) {
        return repository.findAllByPoster(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    NoteEntity one(@PathVariable Long id) {
        return repository.findNote(id);
    }

    @PostMapping(value = "/", consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    NoteEntity createNote(@RequestBody NoteEntity note) {
        return repository.save(note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteNote(@PathVariable Long id) {
        repository.deleteNote(id);
    }

    @PatchMapping("/")
    @ResponseStatus(HttpStatus.OK)
    NoteEntity updateNote(@PathVariable NoteEntity note) {
        return repository.updateNote(note);
    }

}
