package ua.ithillel.lms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import ua.ithillel.lms.exception.MarkNotFoundException;
import ua.ithillel.lms.model.Mark;

@Transactional
public class CustomMarkRepositoryImpl implements CustomMarkRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Mark> getMarks() {
    Query query = entityManager.createNativeQuery("select * from mark", Mark.class);
    List<Mark> result = (List<Mark>) query.getResultList();
    return result;
  }

  @Override
  public Mark getMarkById(long id) throws MarkNotFoundException {
    Query query = entityManager.createNativeQuery("select * from mark where id = :id",
        Mark.class).setParameter("id", id);
    List<Mark> resultList = query.getResultList();
    if (resultList.size() == 0) {
      throw new MarkNotFoundException(id);
    }
    return resultList.get(0);
  }

  @Override
  public Mark addMark(Mark mark) {
    if (!isMarkPresent(mark)) {
      String queryText = "INSERT INTO mark(";
      if (mark.getDiscipline().length() > 0) {
        queryText += "discipline, ";
      }
      queryText += "value) VALUES(";
      if (mark.getDiscipline().length() > 0) {
        queryText += ":discipline, ";
      }
      queryText += ":value)";
      Query query = entityManager.createNativeQuery(queryText);
      if (mark.getDiscipline().length() > 0) {
        query.setParameter("discipline", mark.getDiscipline());
      }
      query.setParameter("value", mark.getValue());
    }
    return mark;
  }

  @Override
  public Mark editMark(Mark mark) {
    String queryText = "UPDATE mark SET ";
    if (mark.getDiscipline().length() > 0) {
      queryText += "discipline = :discipline, ";
    }
    queryText += "value = :value WHERE id = :id";
    Query query = entityManager.createNativeQuery(queryText);
    query.setParameter("id", mark.getId());
    if (mark.getDiscipline().length() > 0) {
      query.setParameter("discipline", mark.getDiscipline());
    }
    query.setParameter("value", mark.getValue());
    query.executeUpdate();
    query.getResultList();
    return mark;
  }

  @Override
  public List<Mark> deleteMark(long id) {
    String queryToChildText = "DELETE FROM student_mark WHERE id = :mId";
    Query queryToChild = entityManager.createNativeQuery(queryToChildText);
    queryToChild.setParameter(":mId", id);
    queryToChild.executeUpdate();
    String queryText = "DELETE FROM mark WHERE id = :id";
    Query query = entityManager.createNativeQuery(queryText);
    query.setParameter("id", id);
    query.executeUpdate();
    query.getResultList();
    return getMarks();
  }

  private boolean isMarkPresent(Mark mark) {
    String queryText = "SELECT * FROM mark WHERE discipline = :discipline AND value = :value";
    Query query = entityManager.createNativeQuery(queryText, Mark.class);
    query.setParameter("discipline", mark.getDiscipline());
    query.setParameter("value", mark.getValue());
    return query.getResultList().size() > 0;
  }
}
