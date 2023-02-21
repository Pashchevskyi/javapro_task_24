package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.StudentDto;
import ua.ithillel.lms.exception.StudentNotFoundException;
import ua.ithillel.lms.model.Student;
import ua.ithillel.lms.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  private final ObjectMapper objectMapper;

  /**
   * Gets all students list
   *
   * @return list of Student entity DTOs
   */
  public List<StudentDto> getStudents() {
    List<StudentDto> result = new ArrayList<>();
    List<Student> students = studentRepository.getStudents();
    students.forEach(student -> result.add(objectMapper.convertValue(student, StudentDto.class)));
    return result;
  }

  /**
   * Gets student from database by id
   *
   * @param id of student in database
   * @return DTO of found Student entity
   * @throws StudentNotFoundException if student has not been found in database
   */
  public StudentDto getStudentById(long id) throws StudentNotFoundException {
    Student foundStudent = studentRepository.getStudentById(id);
    return objectMapper.convertValue(foundStudent, StudentDto.class);
  }

  /**
   * Adds Student to database
   *
   * @param studentDto DTO of adding Student entity
   * @return DTO of added Student entity
   */
  public StudentDto addStudent(StudentDto studentDto) {
    Student student = objectMapper.convertValue(studentDto, Student.class);
    return objectMapper.convertValue(studentRepository.addStudent(student), StudentDto.class);
  }

  /**
   * Updates existing Student
   *
   * @param studentDto DTO of existing Student entity
   * @return DTO of updated Student entity
   * @throws StudentNotFoundException if student has not been found in database
   * @throws JsonMappingException     if student has not been updated in database
   */
  public StudentDto editStudent(StudentDto studentDto)
      throws StudentNotFoundException, JsonMappingException {
    Student studentNewData = objectMapper.convertValue(studentDto, Student.class);
    long updatingStudentId = studentNewData.getId();
    Student studentOldData = studentRepository.getStudentById(updatingStudentId);
    Student studentUpdatedData = objectMapper.updateValue(studentOldData, studentNewData);
    Student updatedStudent = studentRepository.editStudent(studentUpdatedData);
    return objectMapper.convertValue(updatedStudent, StudentDto.class);
  }

  /**
   * Deletes existing student
   *
   * @param id of deleting student in database
   * @return list of Student entity DTOs after deleting
   * @throws StudentNotFoundException if student has not been found in database
   */
  public List<StudentDto> deleteStudent(long id) throws StudentNotFoundException {
    studentRepository.deleteStudent(id);
    return getStudents();
  }
}
