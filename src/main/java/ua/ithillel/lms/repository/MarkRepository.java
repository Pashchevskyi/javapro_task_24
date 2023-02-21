package ua.ithillel.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.model.Mark;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long>, CustomMarkRepository {

}
