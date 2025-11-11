package de.neuefische.todobackend.ExceptionHandler;

import de.neuefische.todobackend.todo.IdService;
import de.neuefische.todobackend.todo.TodoRepository;
import de.neuefische.todobackend.todo.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;



    @Test
    void handleNoSuchElementException() throws Exception {
        /// Given


        /// When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/6"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Todo with id: 6 not found!"));
        /// Then
    }

    @Test
    void handleInvalidInputException() throws Exception {
        /// Given



        /// When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
{
                              "description": null,
                              "status": "OPEN"

}
"""))           .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Einer der Werte ist null"));
    }
}