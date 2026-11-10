package com.example.grades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentController studentController;

    @BeforeEach
    void resetStudents() {
        studentController.getAll().clear();
        studentController.getAll().addAll(List.of(
            new Student(1L, "Ahmed", "SE4111", 95.0),
            new Student(2L, "Sara", "SE4111", 88.5)
        ));
    }

    @Test
    void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldAddNewStudent() throws Exception {
        String json = "{\"name\":\"Khalid\",\"course\":\"SE4111\",\"grade\":91.0}";
        mockMvc.perform(post("/api/students")
                .contentType("application/json")
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Khalid"))
            .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void healthCheckShouldReturnUp() throws Exception {
        mockMvc.perform(get("/api/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"));
    }
}
