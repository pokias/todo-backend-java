package com.todo.todobackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HttpRequestTest {

    @InjectMocks
    NoteController noteController;

    @Mock
    NoteRepository noteRepository;

    @Test
    public void addTestNote() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(noteController.createNote(any(NoteEntity.class))).thenReturn(null);

        NoteEntity note = new NoteEntity("xd testi note", 123L);
        NoteEntity savedNote = noteController.createNote(note);

        assertThat(savedNote.getNote().equals(note.getNote()));
        assertThat(savedNote.getId() != null);
    }
}

