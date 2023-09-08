package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.codingbatapi.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByLanguageId(Long languageId);
    boolean existsByNameAndLanguageId(String name, Long languageId);
    boolean existsByNameAndLanguageIdAndIdNot(String name, Long languageId, Long id);
}
