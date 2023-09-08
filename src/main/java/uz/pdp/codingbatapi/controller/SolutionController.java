package uz.pdp.codingbatapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.Response;
import uz.pdp.codingbatapi.dto.SolutionDTO;
import uz.pdp.codingbatapi.entity.Solution;
import uz.pdp.codingbatapi.marker.OnCreate;
import uz.pdp.codingbatapi.service.SolutionService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/solutions")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Solution> solutions = solutionService.findAll();
        Response response = new Response(solutions, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Solution solution = solutionService.findById(id);
        Response response = new Response(solution, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody SolutionDTO dto) {
        Solution solution = solutionService.create(dto);
        Response response = new Response(solution, HttpStatus.CREATED.name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody SolutionDTO dto, @PathVariable Long id) {
        Solution solution = solutionService.update(dto, id);
        Response response = new Response(solution, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        solutionService.deleteById(id);
        Response response = new Response(HttpStatus.ACCEPTED.name());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
