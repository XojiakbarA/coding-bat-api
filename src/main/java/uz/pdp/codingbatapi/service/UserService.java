package uz.pdp.codingbatapi.service;

import uz.pdp.codingbatapi.dto.UserDTO;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User create(UserDTO dto);
    User update(UserDTO dto, Long id);
    User save(User user);
    void addCompletedProblem(Long id, Problem problem);
    void deleteById(Long id);
    void setAttributes(UserDTO dto, User user);
}
