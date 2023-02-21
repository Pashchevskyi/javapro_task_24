package ua.ithillel.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class StudentMarkKey implements Serializable {

  @Column(name = "student_id")
  long studentId;

  @Column(name = "mark_id")
  long markId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentMarkKey that = (StudentMarkKey) o;
    return studentId == that.studentId && markId == that.markId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, markId);
  }
}
