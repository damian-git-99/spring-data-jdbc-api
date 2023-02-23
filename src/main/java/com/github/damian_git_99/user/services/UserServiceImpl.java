package com.github.damian_git_99.user.services;

import com.github.damian_git_99.user.exceptions.EmailAlreadyTakenException;
import com.github.damian_git_99.user.exceptions.UserNotFoundException;
import com.github.damian_git_99.user.model.Task;
import com.github.damian_git_99.user.model.User;
import com.github.damian_git_99.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public boolean createUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) throw new EmailAlreadyTakenException("Email Already Taken");
        return userRepository.createUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> user = findUserById(id);
        if (user.isEmpty()) throw new UserNotFoundException("User Not Found");
        userRepository.deleteUserById(id);
    }

    @Override
    public boolean updateUser(User user) {
        Long id = user.getId();

        if (id == null) {
            throw new RuntimeException("Bad Request: id cannot be null");
        }

        Optional<User> userOptional = findUserById(id);
        if (userOptional.isEmpty()) throw new UserNotFoundException("User Not Found");

        return userRepository.updateUser(user);
    }

    @Override
    public void createTask(Task task) {
        userRepository.createTask(task);
    }

}
