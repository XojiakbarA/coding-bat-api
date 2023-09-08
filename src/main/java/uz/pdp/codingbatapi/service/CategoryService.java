package uz.pdp.codingbatapi.service;

import uz.pdp.codingbatapi.dto.CategoryDTO;
import uz.pdp.codingbatapi.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAllByLanguageId(Long languageId);
    Category findById(Long id);
    Category create(CategoryDTO dto);
    Category update(CategoryDTO dto, Long id);
    Category save(Category category);
    void deleteById(Long id);
    void setAttributes(CategoryDTO dto, Category category);
}
