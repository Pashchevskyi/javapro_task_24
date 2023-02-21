package ua.ithillel.lms.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ithillel.lms.dto.MarkDto;
import ua.ithillel.lms.exception.MarkNotFoundException;
import ua.ithillel.lms.service.MarkService;

@RestController
@RequestMapping("marks")
@RequiredArgsConstructor
@Slf4j
public class MarkController {

  private final MarkService markService;

  @GetMapping("/")
  public List<MarkDto> getMarks() {
    return markService.getMarks();
  }

  @GetMapping("/{id}")
  public MarkDto getMarkById(@PathVariable long id) {
    try {
      return markService.getMarkById(id);
    } catch (MarkNotFoundException e) {
      log.error(e.getMessage());
    }
    return new MarkDto();
  }

  @PostMapping("/")
  public MarkDto addMark(@RequestBody MarkDto markDto) {
    return markService.addMark(markDto);
  }

  @PutMapping("/")
  public MarkDto editMark(@RequestBody MarkDto markDto) {
    try {
      return markService.editMark(markDto);
    } catch (JsonMappingException e) {
      log.error(e.getMessage());
    } catch (MarkNotFoundException e) {
      log.error(e.getMessage());
    }
    return new MarkDto();
  }

  @DeleteMapping("/{id}")
  public List<MarkDto> deleteMark(@PathVariable long id) {
    return markService.deleteMark(id);
  }
}
