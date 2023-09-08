package uz.pdp.codingbatapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.Response;
import uz.pdp.codingbatapi.dto.LanguageDTO;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Language;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Language> languages = languageService.findAll();
        Response response = new Response(languages, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Language language = languageService.findById(id);
        Response response = new Response(language, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Response> getAllCategoriesByLanguageId(@PathVariable Long id) {
        List<Category> categories = categoryService.findAllByLanguageId(id);
        Response response = new Response(categories, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody LanguageDTO dto) {
        Language language = languageService.create(dto);
        Response response = new Response(language, HttpStatus.CREATED.name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody LanguageDTO dto, @PathVariable Long id) {
        Language language = languageService.update(dto, id);
        Response response = new Response(language, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        languageService.deleteById(id);
        Response response = new Response(HttpStatus.ACCEPTED.name());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
