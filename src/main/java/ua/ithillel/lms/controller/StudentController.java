package ua.ithillel.lms.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ithillel.lms.dto.StudentDto;
import ua.ithillel.lms.exception.StudentNotFoundException;
import ua.ithillel.lms.service.StudentService;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

  private final StudentService studentService;

  @GetMapping("/")
  public List<StudentDto> getStudents() {
    return studentService.getStudents();
  }

  @GetMapping("/{id}")
  public StudentDto getStudentById(@PathVariable long id) {
    try {
      return studentService.getStudentById(id);
    } catch (StudentNotFoundException e) {
      log.error(e.getMessage());
    }
    return new StudentDto();
  }

  @PostMapping("/")
  public StudentDto addStudent(@RequestBody StudentDto studentDto) {
    return studentService.addStudent(studentDto);
  }

  @PutMapping("/")
  public StudentDto editStudent(@RequestBody StudentDto studentDto) {
    try {
      return studentService.editStudent(studentDto);
    } catch (JsonMappingException e) {
      log.error(e.getMessage());
    } catch (StudentNotFoundException e) {
      log.error(e.getMessage());
    }
    return new StudentDto();
  }

  @DeleteMapping("/{id}")
  public List<StudentDto> deleteStudent(@PathVariable long id) {
    try {
      return studentService.deleteStudent(id);
    } catch (StudentNotFoundException e) {
      log.error(e.getMessage());
    }
    return new ArrayList<>();
  }
}
