package ua.ithillel.lms.dto;

import java.util.List;
import lombok.Data;

@Data
public class StudentDto {
  private long id;
  private String name;
  private String email;

  private List<StudentMarkDto> marks;
}
