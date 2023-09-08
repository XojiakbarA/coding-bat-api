package uz.pdp.codingbatapi.service;

import uz.pdp.codingbatapi.dto.LanguageDTO;
import uz.pdp.codingbatapi.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAll();
    Language findById(Long id);
    Language create(LanguageDTO dto);
    Language update(LanguageDTO dto, Long id);
    Language save(Language language);
    void deleteById(Long id);
}
