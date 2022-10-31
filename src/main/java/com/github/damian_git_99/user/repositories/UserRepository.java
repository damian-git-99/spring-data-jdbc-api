package com.github.damian_git_99.user.repositories;

import com.github.damian_git_99.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserById(Long id);

    boolean createUser(User user);

    List<User> findAllUsers();

    void deleteUserById(Long id);

    Optional<User> findUserByEmail(String email);

    boolean updateUser(User user);

}
