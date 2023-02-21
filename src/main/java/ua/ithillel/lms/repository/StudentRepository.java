package ua.ithillel.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>, CustomStudentRepository {

}
