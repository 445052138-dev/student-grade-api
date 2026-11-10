package com.example.grades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final List<Student> students = new ArrayList<>(List.of(
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
            .filter(student -> student.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @PostMapping("/students")
    public Student add(@RequestBody Student student) {
        student.setId(students.stream().mapToLong(Student::getId).max().orElse(0L) + 1L);
        students.add(student);
        logger.info("Added student: id={}, name={}", student.getId(), student.getName());
        return student;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        logger.info("Health check called");
        return Map.of("status", "UP");
    }
}
