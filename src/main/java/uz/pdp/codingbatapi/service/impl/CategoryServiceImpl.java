package uz.pdp.codingbatapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.dto.CategoryDTO;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Language;
import uz.pdp.codingbatapi.exception.ResourceExistsException;
import uz.pdp.codingbatapi.exception.ResourceNotFoundException;
import uz.pdp.codingbatapi.repository.CategoryRepository;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.LanguageService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LanguageService languageService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByLanguageId(Long languageId) {
        return categoryRepository.findAllByLanguageId(languageId);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Category.class.getSimpleName(), id)
        );
    }

    @Override
    public Category create(CategoryDTO dto) {
        if (categoryRepository.existsByNameAndLanguageId(dto.getName(), dto.getLanguageId())) {
            throw new ResourceExistsException("A category with name = " + dto.getName() + " and language_id = " + dto.getLanguageId() + " already exists");
        }
        Category category = new Category();

        setAttributes(dto, category);

        return save(category);
    }

    @Override
    public Category update(CategoryDTO dto, Long id) {
        if (categoryRepository.existsByNameAndLanguageIdAndIdNot(dto.getName(), dto.getLanguageId(), id)) {
            throw new ResourceExistsException("A category with name = " + dto.getName() + " and language_id = " + dto.getLanguageId() + " already exists");
        }
        Category category = findById(id);

        setAttributes(dto, category);

        return save(category);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw  new ResourceNotFoundException(Category.class.getSimpleName(), id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void setAttributes(CategoryDTO dto, Category category) {
        if (dto.getLanguageId() != null) {
            Language language = languageService.findById(dto.getLanguageId());
            category.setLanguage(language);
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            category.setDescription(dto.getDescription());
        }
    }
}
