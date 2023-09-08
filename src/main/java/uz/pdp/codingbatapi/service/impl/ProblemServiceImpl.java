package uz.pdp.codingbatapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.dto.ProblemDTO;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.exception.ResourceExistsException;
import uz.pdp.codingbatapi.exception.ResourceNotFoundException;
import uz.pdp.codingbatapi.repository.ProblemRepository;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.ProblemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public List<Problem> findAllByCategoryId(Long categoryId) {
        return problemRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Problem findById(Long id) {
        return problemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Problem.class.getSimpleName(), id)
        );
    }

    @Override
    public Problem create(ProblemDTO dto) {
        if (problemRepository.existsByNameAndCategoryId(dto.getName(), dto.getCategoryId())) {
            throw new ResourceExistsException("A problem with name = " + dto.getName() + " and category_id = " + dto.getCategoryId() + " already exists");
        }
        Problem problem = new Problem();

        setAttributes(dto, problem);

        return save(problem);
    }

    @Override
    public Problem update(ProblemDTO dto, Long id) {
        if (problemRepository.existsByNameAndCategoryIdAndIdNot(dto.getName(), dto.getCategoryId(), id)) {
            throw new ResourceExistsException("A problem with name = " + dto.getName() + " and category_id = " + dto.getCategoryId() + " already exists");
        }
        Problem problem = findById(id);

        setAttributes(dto, problem);

        return save(problem);
    }

    @Override
    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public void deleteById(Long id) {
        Problem problem = findById(id);
        for (User user : new ArrayList<>(problem.getUsers())) {
            problem.removeUser(user);
        }
        problemRepository.deleteById(id);
    }

    @Override
    public void setAttributes(ProblemDTO dto, Problem problem) {
        if (dto.getCategoryId() != null) {
            Category category = categoryService.findById(dto.getCategoryId());
            problem.setCategory(category);
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            problem.setName(dto.getName());
        }
        if (dto.getInstruction() != null && !dto.getInstruction().isBlank()) {
            problem.setInstruction(dto.getInstruction());
        }
        if (dto.getCorrectSolution() != null && !dto.getCorrectSolution().isBlank()) {
            problem.setCorrectSolution(dto.getCorrectSolution());
        }
    }
}
