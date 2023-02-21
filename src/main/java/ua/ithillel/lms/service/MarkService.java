package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.MarkDto;
import ua.ithillel.lms.exception.MarkNotFoundException;
import ua.ithillel.lms.model.Mark;
import ua.ithillel.lms.repository.MarkRepository;

@Service
@RequiredArgsConstructor
public class MarkService {

  private final MarkRepository markRepository;

  private final ObjectMapper objectMapper;

  /**
   * Gets marks of all students
   *
   * @return list of Mark entity DTOs
   */
  public List<MarkDto> getMarks() {
    List<MarkDto> result = new ArrayList<>();
    List<Mark> marks = markRepository.getMarks();
    marks.forEach(mark -> result.add(objectMapper.convertValue(mark, MarkDto.class)));
    return result;
  }

  /**
   * Gets mark from database by id
   *
   * @param id of mark in database
   * @return DTO of found Mark entity
   * @throws MarkNotFoundException if mark has not been found in database
   */
  public MarkDto getMarkById(long id) throws MarkNotFoundException {
    Mark foundMark = markRepository.getMarkById(id);
    return objectMapper.convertValue(foundMark, MarkDto.class);
  }

  /**
   * Adds Mark to database
   *
   * @param markDto DTO of adding Mark entity
   * @return DTO of added Mark entity
   */
  public MarkDto addMark(MarkDto markDto) {
    Mark mark = objectMapper.convertValue(markDto, Mark.class);
    return objectMapper.convertValue(markRepository.addMark(mark), MarkDto.class);
  }

  /**
   * Updates existing Mark
   *
   * @param markDto DTO of updating Mark entity
   * @return DTO of updated Mark entity
   * @throws MarkNotFoundException if mark has not been found in database
   * @throws JsonMappingException  if mark has not been updated in database
   */
  public MarkDto editMark(MarkDto markDto) throws MarkNotFoundException, JsonMappingException {
    Mark markNewData = objectMapper.convertValue(markDto, Mark.class);
    long updatingMarkId = markNewData.getId();
    Mark markOldData = markRepository.getMarkById(updatingMarkId);
    Mark markUpdatedData = objectMapper.updateValue(markOldData, markNewData);
    return objectMapper.convertValue(markRepository.editMark(markUpdatedData), MarkDto.class);
  }

  /**
   * Deletes existing mark
   *
   * @param id of deleting mark in database
   * @return list of Mark entity DTOs after deleting
   */
  public List<MarkDto> deleteMark(long id) {
    markRepository.deleteMark(id);
    return getMarks();
  }
}
