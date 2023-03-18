package com.todo.todobackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {


    @Query(value = "DELETE FROM NOTE WHERE id = ?!", nativeQuery = true)
    void deleteNote(Long id);


    @Query(value = "SELECT * from NOTE", nativeQuery = true)
    List<NoteEntity> getAllNotes();

    @Query(value = "SELECT * from NOTE where id = ?1", nativeQuery = true)
    NoteEntity getNote(Long id);

    @Query(value = "SELECT * from NOTE where poster = ?1", nativeQuery = true)
    List<NoteEntity> getNotesByPoster(Long posterId);
}
