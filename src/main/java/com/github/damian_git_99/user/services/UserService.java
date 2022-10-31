package com.github.damian_git_99.user.services;

import com.github.damian_git_99.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    boolean createUser(User user);

    List<User> findAllUsers();

    void deleteUserById(Long id);

    boolean updateUser(User user);
}
