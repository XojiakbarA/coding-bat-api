package uz.pdp.codingbatapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.dto.LanguageDTO;
import uz.pdp.codingbatapi.entity.Language;
import uz.pdp.codingbatapi.exception.ResourceExistsException;
import uz.pdp.codingbatapi.exception.ResourceNotFoundException;
import uz.pdp.codingbatapi.repository.LanguageRepository;
import uz.pdp.codingbatapi.service.LanguageService;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Language.class.getSimpleName(), id)
        );
    }

    @Override
    public Language create(LanguageDTO dto) {
        if (languageRepository.existsByName(dto.getName())) {
            throw new ResourceExistsException("A language with name = " + dto.getName() + " already exists");
        }
        Language language = new Language();
        language.setName(dto.getName());
        return save(language);
    }

    @Override
    public Language update(LanguageDTO dto, Long id) {
        if (languageRepository.existsByNameAndIdNot(dto.getName(), id)) {
            throw new ResourceExistsException("A language with name = " + dto.getName() + " already exists");
        }
        Language language = findById(id);
        if (dto.getName() != null && !language.getName().isBlank()) {
            language.setName(dto.getName());
        }
        language.setName(dto.getName());
        return save(language);
    }

    @Override
    public Language save(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteById(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new ResourceNotFoundException(Language.class.getSimpleName(), id);
        }
        languageRepository.deleteById(id);
    }
}
