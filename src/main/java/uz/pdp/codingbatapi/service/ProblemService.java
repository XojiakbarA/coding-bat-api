package uz.pdp.codingbatapi.service;

import uz.pdp.codingbatapi.dto.ProblemDTO;
import uz.pdp.codingbatapi.entity.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> findAll();
    List<Problem> findAllByCategoryId(Long categoryId);
    Problem findById(Long id);
    Problem create(ProblemDTO dto);
    Problem update(ProblemDTO dto, Long id);
    Problem save(Problem problem);
    void deleteById(Long id);
    void setAttributes(ProblemDTO dto, Problem problem);
}
