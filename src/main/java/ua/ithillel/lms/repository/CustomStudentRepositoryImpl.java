package ua.ithillel.lms.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.lms.exception.StudentNotFoundException;
import ua.ithillel.lms.model.Student;
import ua.ithillel.lms.model.StudentMark;

public class CustomStudentRepositoryImpl implements CustomStudentRepository {

  @PersistenceContext
  private EntityManager entityManager;

  private MarkRepository markRepository;

  private StudentMarkRepository studentMarkRepository;

  @Override
  public List<Student> getStudents() {
    Query query = entityManager.createQuery("select S from Student S", Student.class);
    return query.getResultList();
  }

  @Override
  public Student getStudentById(long id) throws StudentNotFoundException {
    Query query = entityManager.createQuery("select S from Student S where S.id = :id",
        Student.class).setParameter("id", id);
    List<Student> resultList = (List<Student>) query.getResultList();
    if (resultList.size() == 0) {
      throw new StudentNotFoundException(id);
    }
    return resultList.get(0);
  }

  @Override
  @Transactional
  public Student addStudent(Student student) {
    Session session = entityManager.unwrap(Session.class);

    if (!isStudentPresent(student)) {
      String queryText = "INSERT INTO student(";
      if (student.getName().length() > 0) {
        queryText += "name";
      }
      if (student.getEmail().length() > 0) {
        queryText += (student.getName().length() > 0 ? ", " : "") + "email) VALUES(";
      }
      if (student.getName().length() > 0) {
        queryText += "'" + student.getName() + "'";
      }
      if (student.getEmail().length() > 0) {
        queryText += (student.getName().length() > 0 ? ", '" : "'") + student.getEmail() + "')";
      }
      session.createNativeQuery(queryText);
      session.persist(student);
    }
    StringBuilder marksQueryTextBuilder = new StringBuilder(
        "INSERT INTO student_mark(student_id, mark_id) VALUES(");
    long i = 0;
    List<StudentMark> studentMarks = student.getMarks();
    for (StudentMark studentMark : studentMarks) {
      marksQueryTextBuilder.append(studentMark.getStudent().getId());
      marksQueryTextBuilder.append(", ");
      marksQueryTextBuilder.append(studentMark.getMark().getId());
      marksQueryTextBuilder.append((i < studentMarks.size() - 1) ? "), " : ")");
      i++;
    }
    System.out.println(marksQueryTextBuilder);
    session.createNativeQuery(marksQueryTextBuilder.toString());
    session.flush();
    session.clear();
    session.close();

    return student;
  }

  @Override
  public Student editStudent(Student student) {
    String queryText = "UPDATE Student SET ";
    if (student.getName().length() > 0) {
      queryText += "name = " + student.getName();
    }
    if (student.getEmail().length() > 0) {
      queryText += (student.getName().length() > 0 ? ", " : "") + "email = " + student.getEmail();
    }
    queryText += " WHERE id = " + student.getId();
    Query query = entityManager.createQuery(queryText);
    query.setParameter("id", student.getId());
    query.executeUpdate();

    for (StudentMark studentMark : student.getMarks()) {
      markRepository.addMark(studentMark.getMark());
      studentMarkRepository.addStudentMark(studentMark);
    }

    return student;
  }

  @Override
  public List<Student> deleteStudent(long id) {
    String queryToChildText = "DELETE FROM StudentMark WHERE student = :sId";
    Query queryToChild = entityManager.createQuery(queryToChildText);
    queryToChild.setParameter("sId", id);
    queryToChild.executeUpdate();
    String queryText = "DELETE FROM Student WHERE id = :id";
    Query query = entityManager.createQuery(queryText);
    query.setParameter("id", id);
    query.executeUpdate();
    return getStudents();
  }

  private boolean isStudentPresent(Student student) {
    String queryText = "SELECT S FROM Student S WHERE S.name = :name AND S.email = :email";
    Query query = entityManager.createQuery(queryText, Student.class);
    query.setParameter("name", student.getName());
    query.setParameter("email", student.getEmail());
    return query.getResultList().size() > 0;
  }
}
