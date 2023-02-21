package ua.ithillel.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.model.StudentMark;

@Repository
public interface StudentMarkRepository extends CrudRepository<StudentMark, Long>,
    CustomStudentMarkRepository {

}
