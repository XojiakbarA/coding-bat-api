package uz.pdp.codingbatapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatapi.Response;
import uz.pdp.codingbatapi.dto.UserDTO;
import uz.pdp.codingbatapi.entity.Solution;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.marker.OnCreate;
import uz.pdp.codingbatapi.service.SolutionService;
import uz.pdp.codingbatapi.service.UserService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SolutionService solutionService;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        List<User> users = userService.findAll();
        Response response = new Response(users, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        User user = userService.findById(id);
        Response response = new Response(user, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/solutions")
    public ResponseEntity<Response> getAllSolutionsByUserId(@PathVariable Long id) {
        List<Solution> solutions = solutionService.findAllByUserId(id);
        Response response = new Response(solutions, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody UserDTO dto) {
        User user = userService.create(dto);
        Response response = new Response(user, HttpStatus.CREATED.name());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@Valid @RequestBody UserDTO dto, @PathVariable Long id) {
        User user = userService.update(dto, id);
        Response response = new Response(user, HttpStatus.OK.name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        userService.deleteById(id);
        Response response = new Response(HttpStatus.ACCEPTED.name());
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
