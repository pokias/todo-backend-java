package com.todo.todobackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NoteController.class)
public class HttpRequestTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteController noteController;

    @Test
    public void getNotes() throws Exception {

    }
}
