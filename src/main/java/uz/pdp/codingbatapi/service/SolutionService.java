package uz.pdp.codingbatapi.service;

import uz.pdp.codingbatapi.dto.SolutionDTO;
import uz.pdp.codingbatapi.entity.Solution;

import java.util.List;

public interface SolutionService {
    List<Solution> findAll();
    List<Solution> findAllByProblemId(Long problemId);
    List<Solution> findAllByUserId(Long userId);
    Solution findById(Long id);
    Solution create(SolutionDTO dto);
    Solution update(SolutionDTO dto, Long id);
    Solution save(Solution solution);
    void deleteById(Long id);
    void setAttributes(SolutionDTO dto, Solution solution);
}
