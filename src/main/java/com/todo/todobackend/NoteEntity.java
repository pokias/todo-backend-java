package com.todo.todobackend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table(name = "NOTE")
public class NoteEntity {
    private @jakarta.persistence.Id
    @GeneratedValue Long Id;
    private String note;
    private Long poster;

    NoteEntity() {
    }

    public NoteEntity(String note, Long poster) {
        this.note = note;
        this.poster = poster;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getPoster() {
        return poster;
    }

    public void setPoster(Long poster) {
        this.poster = poster;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
