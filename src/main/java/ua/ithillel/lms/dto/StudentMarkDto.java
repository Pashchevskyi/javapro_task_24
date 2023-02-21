package ua.ithillel.lms.dto;

import lombok.Data;

@Data
public class StudentMarkDto {
  private StudentDto student;

  private MarkDto mark;
}
