package ua.ithillel.lms.repository;

import java.util.List;
import ua.ithillel.lms.exception.StudentNotFoundException;
import ua.ithillel.lms.model.Student;

public interface CustomStudentRepository {

  List<Student> getStudents();

  Student getStudentById(long id) throws StudentNotFoundException;

  Student addStudent(Student student);

  Student editStudent(Student student);

  List<Student> deleteStudent(long id);
}
