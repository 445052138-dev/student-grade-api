package com.example.grades;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private List<Student> students = new ArrayList<>(List.of(
        new Student(1L, "Ahmed", "SE4111", 95.0),
        new Student(2L, "Sara", "SE4111", 88.5)
    ));

    @GetMapping("/students")
    public List<Student> getAll() {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable Long id) {
        return students.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @PostMapping("/students")
    public Student add(@RequestBody Student s) {
        s.setId((long) (students.size() + 1));
        students.add(s);
        logger.info("Added student: id={}, name={}", s.getId(), s.getName());
        return s;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        logger.info("Health check called");
        return Map.of("status", "UP");
    }
}
