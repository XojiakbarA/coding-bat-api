package uz.pdp.codingbatapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.Response;
import uz.pdp.codingbatapi.dto.ProblemDTO;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.entity.Solution;
import uz.pdp.codingbatapi.marker.OnCreate;
import uz.pdp.codingbatapi.service.ProblemService;
import uz.pdp.codingbatapi.service.SolutionService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/problems")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<Problem> problems = problemService.findAll();
        Response response = new Response(problems, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        Problem problem = problemService.findById(id);
        Response response = new Response(problem, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/solutions")
    public ResponseEntity<Response> getAllSolutionsByProblemId(@PathVariable Long id) {
        List<Solution> solutions = solutionService.findAllByProblemId(id);
        Response response = new Response(solutions, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody ProblemDTO dto) {
        Problem problem = problemService.create(dto);
        Response response = new Response(problem, HttpStatus.CREATED.name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody ProblemDTO dto, @PathVariable Long id) {
        Problem problem = problemService.update(dto, id);
        Response response = new Response(problem, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        problemService.deleteById(id);
        Response response = new Response(HttpStatus.ACCEPTED.name());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
