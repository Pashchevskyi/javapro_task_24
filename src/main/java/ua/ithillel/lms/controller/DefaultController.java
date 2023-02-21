package ua.ithillel.lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

  @GetMapping("/")
  public String indexAction() {
    return "It is recommended to use this REST API Application via Postman.\n"
        + "\n"
        + "If you use Postman, please, do the following.\n"
        + "\n"
        + "1. Create new collection, name it, for examole, \"hillel_javapro_rest_api\".\n"
        + "2. In this collection all request URLs will be http://localhost:8080 *\n"
        + "3. Create new GET request in Postman to URN \"students/\" to get all students list\n"
        + "   in JSON format.\n"
        + "4. Create new GET request in Postman to URN \"students/{id}\" (for example\n"
        + "   \"students/1\") to get student by id.\n"
        + "5. Create new POST request in Postman to URN \"students/\" to add new student."
        + "6. Create new PUT request in Postman to URN \"students/\" to edit existing student."
        + "7. Create new DELETE request in Postman to URN \"students/{id}\" to delete student."
        + "8. Create new GET request in Postman to URN \"marks/{id}\" (for example\n"
        + "   \"marks/1\") to get mark by id.\n"
        + "9. Create new POST request in Postman to URN \"marks/\" to add new mark."
        + "6. Create new PUT request in Postman to URN \"marks/\" to edit existing mark."
        + "7. Create new DELETE request in Postman to URN \"marks/{id}\" to delete mark."
        + " * port for Tomcat application server may be changed via settings in "
        + "./src/main/resources/application.properties file (for example, server.port=8001 , if you"
        + " want to run the application on 8001 port)";
  }
}
