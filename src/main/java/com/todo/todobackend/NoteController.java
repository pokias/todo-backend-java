package com.todo.todobackend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
private final NoteRepository repository;

    NoteController(NoteRepository repository){
        this.repository=repository;
    }

    @GetMapping("/notes")
    List<NoteEntity> all(){
        return repository.findAll();
    }

    @GetMapping("/note/{id}")
    NoteEntity one(@PathVariable long id){
    return repository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
    }

    @PostMapping("/note")
    NoteEntity createNote(@RequestBody NoteEntity note) {
      return repository.save(note);
    };

    @PutMapping("/note")
    NoteEntity createNotePut(@RequestBody NoteEntity note) {
        return repository.save(note);
    };

    @DeleteMapping("/note/{id}")
    void deleteNote(@PathVariable Long id){
        repository.deleteById(id);
    }

}
