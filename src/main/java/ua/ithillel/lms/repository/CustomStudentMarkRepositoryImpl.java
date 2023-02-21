package ua.ithillel.lms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ua.ithillel.lms.model.StudentMark;

public class CustomStudentMarkRepositoryImpl implements CustomStudentMarkRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public StudentMark addStudentMark(StudentMark studentMark) {
    if (!isStudentMarkPresent(studentMark)) {
      String queryText = "INSERT INTO student_mark(student_id, mark_id) VALUES(:s_id, :m_id)";
      Query query = entityManager.createNativeQuery(queryText);
      query.setParameter("s_id", studentMark.getStudent());
      query.setParameter("m_id", studentMark.getMark());
      query.executeUpdate();
    }
    return studentMark;
  }

  private boolean isStudentMarkPresent(StudentMark studentMark) {
    String queryText = "SELECT * FROM student_mark WHERE student_id = :s_id AND mark_id = :m_id";
    Query query = entityManager.createNativeQuery(queryText, StudentMark.class);
    query.setParameter("s_id", studentMark.getStudent());
    query.setParameter("m_id", studentMark.getMark());
    return query.getResultStream().count() > 0;
  }
}
