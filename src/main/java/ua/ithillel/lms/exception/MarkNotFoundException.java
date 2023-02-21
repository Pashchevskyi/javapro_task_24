package ua.ithillel.lms.exception;

public class MarkNotFoundException extends Exception {

  public MarkNotFoundException(long id) {
    super("Mark with ID#" + id + " has not been found");
  }
}
