package uz.pdp.codingbatapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.dto.SolutionDTO;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.entity.Solution;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.exception.ResourceNotFoundException;
import uz.pdp.codingbatapi.repository.SolutionRepository;
import uz.pdp.codingbatapi.service.ProblemService;
import uz.pdp.codingbatapi.service.SolutionService;
import uz.pdp.codingbatapi.service.UserService;

import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProblemService problemService;

    @Override
    public List<Solution> findAll() {
        return solutionRepository.findAll();
    }

    @Override
    public List<Solution> findAllByProblemId(Long problemId) {
        return solutionRepository.findAllByProblemId(problemId);
    }

    @Override
    public List<Solution> findAllByUserId(Long userId) {
        return solutionRepository.findAllByUserId(userId);
    }

    @Override
    public Solution findById(Long id) {
        return solutionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Solution.class.getSimpleName(), id)
        );
    }

    @Override
    public Solution create(SolutionDTO dto) {
        Solution solution = new Solution();

        setAttributes(dto, solution);

        Solution savedSolution = save(solution);

        if (savedSolution.getContent().equals(savedSolution.getProblem().getCorrectSolution())) {
            userService.addCompletedProblem(savedSolution.getUser().getId(), savedSolution.getProblem());
        }

        return savedSolution;
    }

    @Override
    public Solution update(SolutionDTO dto, Long id) {
        Solution solution = findById(id);

        setAttributes(dto, solution);

        return save(solution);
    }

    @Override
    public Solution save(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public void deleteById(Long id) {
        if (!solutionRepository.existsById(id)) {
            throw new ResourceNotFoundException(Solution.class.getSimpleName(), id);
        }
        solutionRepository.deleteById(id);
    }

    @Override
    public void setAttributes(SolutionDTO dto, Solution solution) {
        if (dto.getUserId() != null) {
            User user = userService.findById(dto.getUserId());
            solution.setUser(user);
        }
        if (dto.getProblemId() != null) {
            Problem problem = problemService.findById(dto.getProblemId());
            solution.setProblem(problem);
        }
        if (dto.getContent() != null && !dto.getContent().isBlank()) {
            solution.setContent(dto.getContent());
        }
    }
}
