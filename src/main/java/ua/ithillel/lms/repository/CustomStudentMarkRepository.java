package ua.ithillel.lms.repository;

import ua.ithillel.lms.model.Mark;
import ua.ithillel.lms.model.Student;
import ua.ithillel.lms.model.StudentMark;

public interface CustomStudentMarkRepository {

  StudentMark addStudentMark(StudentMark studentMark);
}
