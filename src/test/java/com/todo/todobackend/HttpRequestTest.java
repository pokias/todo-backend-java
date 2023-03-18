package com.todo.todobackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class HttpRequestTest {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    @Transactional
    void setup() {
        noteRepository.deleteAll();
    }

    @Before
    @Transactional
    public void before() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addTestNote() throws Exception {

        NoteEntity note = new NoteEntity("testi note", 123L);

        ResultActions response = mockMvc.perform(post("/note/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note)));


        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.poster", is(note.getPoster().intValue())))
                .andExpect(jsonPath("$.note", is(note.getNote())));

    }

    @Test
    public void addTestNoteAndRemoveIt() throws Exception {

        NoteEntity note = new NoteEntity("testi note", 123L);

        //create note to db
        mockMvc.perform(post("/note/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note)));

        //fetch node from db
        List<NoteEntity> notes = noteRepository.findAll();
        notes.forEach(noteEntity -> {
            try {
                ResultActions response2 = mockMvc.perform(delete("/note/" + noteEntity.getId())).andExpect(status().isOk());
                response2.andDo(print()).
                        andExpect(status().isOk());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Test
    public void searchNote() throws Exception {
        NoteEntity note = new NoteEntity("testi note", 123L);
        //create note to db
        mockMvc.perform(post("/note/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note)));

        mockMvc.perform(get("/note/")).andDo(print())
                .andExpect(status().isOk());
    }


}

