package uz.pdp.codingbatapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.Response;
import uz.pdp.codingbatapi.dto.CategoryDTO;
import uz.pdp.codingbatapi.entity.Category;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.marker.OnCreate;
import uz.pdp.codingbatapi.service.CategoryService;
import uz.pdp.codingbatapi.service.ProblemService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProblemService problemService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Category> categories = categoryService.findAll();
        Response response = new Response(categories, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        Response response = new Response(category, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/problems")
    public ResponseEntity<Response> getAllProblemsByCategoryId(@PathVariable Long id) {
        List<Problem> problems = problemService.findAllByCategoryId(id);
        Response response = new Response(problems, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody CategoryDTO dto) {
        Category category = categoryService.create(dto);
        Response response = new Response(category, HttpStatus.CREATED.name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody CategoryDTO dto, @PathVariable Long id) {
        Category category = categoryService.update(dto, id);
        Response response = new Response(category, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        Response response = new Response(HttpStatus.ACCEPTED.name());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
