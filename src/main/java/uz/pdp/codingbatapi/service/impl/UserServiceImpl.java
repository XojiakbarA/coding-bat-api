package uz.pdp.codingbatapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatapi.dto.UserDTO;
import uz.pdp.codingbatapi.entity.Problem;
import uz.pdp.codingbatapi.entity.User;
import uz.pdp.codingbatapi.exception.ResourceExistsException;
import uz.pdp.codingbatapi.exception.ResourceNotFoundException;
import uz.pdp.codingbatapi.repository.UserRepository;
import uz.pdp.codingbatapi.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(User.class.getSimpleName(), id)
        );
    }

    @Override
    public User create(UserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResourceExistsException("A user with name = " + dto.getEmail() + " already exists");
        }
        User user = new User();

        setAttributes(dto, user);

        return save(user);
    }

    @Override
    public User update(UserDTO dto, Long id) {
        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new ResourceExistsException("A user with name = " + dto.getEmail() + " already exists");
        }
        User user = findById(id);

        setAttributes(dto, user);

        return save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addCompletedProblem(Long id, Problem problem) {
        User user = findById(id);
        user.addProblem(problem);
        save(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        for (Problem problem : new ArrayList<>(user.getProblems())) {
            user.removeProblem(problem);
        }
        userRepository.deleteById(id);
    }

    @Override
    public void setAttributes(UserDTO dto, User user) {
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }
    }
}
