package cursoSpringBoot.controllers;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cursoSpringBoot.domain.Student;

@RestController
@RequestMapping({ "/students", "/estudiantes" })
public class StudentController {
    private final List<Student> students = new CopyOnWriteArrayList<>(List.of(
            new Student(1, "Ana Perez", "ana@example.com", 20),
            new Student(2, "Luis Gomez", "luis@example.com", 22)));
    private final AtomicInteger nextId = new AtomicInteger(3);

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId(nextId.getAndIncrement());
        students.add(student);

        return ResponseEntity
                .created(URI.create("/students/" + student.getId()))
                .body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {
        return findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setAge(updatedStudent.getAge());
                    return ResponseEntity.ok(student);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> partiallyUpdateStudent(
            @PathVariable int id,
            @RequestBody Student changes) {
        return findById(id)
                .map(student -> {
                    if (changes.getName() != null) {
                        student.setName(changes.getName());
                    }
                    if (changes.getEmail() != null) {
                        student.setEmail(changes.getEmail());
                    }
                    if (changes.getAge() != null) {
                        student.setAge(changes.getAge());
                    }
                    return ResponseEntity.ok(student);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        return removed
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private java.util.Optional<Student> findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}
