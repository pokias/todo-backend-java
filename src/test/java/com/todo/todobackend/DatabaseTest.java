package com.todo.todobackend;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoteEntity.class)
public class DatabaseTest {
    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void getAllNotes() {
        NoteEntity note = noteRepository.save(new NoteEntity("this is long note", 2L));
        Optional<NoteEntity> note2 = noteRepository.findById(note.getId());

        assertNotNull(note2);
    }
}
