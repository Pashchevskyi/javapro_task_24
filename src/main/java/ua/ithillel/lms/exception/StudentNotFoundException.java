package ua.ithillel.lms.exception;

public class StudentNotFoundException extends Exception {

  public StudentNotFoundException(long id) {
    super("Student with ID#" + id + " has not been found");
  }
}
