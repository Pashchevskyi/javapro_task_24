package ua.ithillel.lms.repository;

import java.util.List;
import ua.ithillel.lms.exception.MarkNotFoundException;
import ua.ithillel.lms.model.Mark;

public interface CustomMarkRepository {

  List<Mark> getMarks();

  Mark getMarkById(long id) throws MarkNotFoundException;

  Mark addMark(Mark mark);

  Mark editMark(Mark mark);

  List<Mark> deleteMark(long id);
}
