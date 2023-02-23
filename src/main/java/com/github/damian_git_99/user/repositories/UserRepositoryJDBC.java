package com.github.damian_git_99.user.repositories;


import com.github.damian_git_99.user.model.Task;
import com.github.damian_git_99.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJDBC implements UserRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserRepositoryJDBC(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try {
            String query = "SELECT * FROM users WHERE users.id = ?";
            User user = jdbc.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), id);
            return Optional.ofNullable(user);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        try {
            String query = "SELECT * FROM users WHERE users.email = ?";
            User user = jdbc.queryForObject(query, BeanPropertyRowMapper.newInstance(User.class), email);
            return Optional.ofNullable(user);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean createUser(User user) {
        String query = "INSERT INTO users (username, email, phone) VALUES (?,?,?)";
        int result = jdbc.update(query, user.getUsername(), user.getEmail(), user.getPhone());
        return result == 1;
    }

    @Override
    public List<User> findAllUsers() {
        String query = "SELECT * FROM users";
        return jdbc.query(query, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public void deleteUserById(Long id) {
        String query = "DELETE FROM users WHERE users.id = ?";
        jdbc.update(query, id);
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE users SET username=?, email=?, phone=? WHERE users.id = ?";
        int result = jdbc.update(query, user.getUsername(), user.getEmail(), user.getPhone(), user.getId());
        return result == 1;
    }

    @Override
    public boolean createTask(Task task) {
        String query = "INSERT INTO tasks (name, description, user_id) VALUES (?,?,?)";
        int result = jdbc.update(query, task.getName(), task.getDescription(), task.getUserId());
        return result == 1;
    }

}
