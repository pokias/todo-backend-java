package com.todo.todobackend;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class TodoBackendApplicationTests {

	@Autowired
	private NoteController controller;

	@Test
	public void contectLoadsWithController() throws Exception{
		assertThat(controller).isNotNull();
	}

}
