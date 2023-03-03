package com.github.damian_git_99.user.repositories;


import com.github.damian_git_99.user.model.Task;
import com.github.damian_git_99.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class UserRepositoryJDBC implements UserRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserRepositoryJDBC(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        String sql = "SELECT u.id, u.username, u.email, u.phone, t.id as task_id, t.name as task_name, t.description as task_description " +
                "FROM users u " +
                "LEFT JOIN tasks t ON u.id = t.user_id " +
                "WHERE u.id = ?";

        User user = jdbc.query(sql, new Object[]{userId}, new int[]{Types.INTEGER}, rs -> {
            User u = null;
            while (rs.next()) {
                if (u == null) {
                    u = User.builder()
                            .id(rs.getLong("id"))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .phone(rs.getString("phone"))
                            .build();
                }

                Task task = Task.builder()
                        .id(rs.getLong("task_id"))
                        .name(rs.getString("task_name"))
                        .description(rs.getString("task_description"))
                        .userId(rs.getLong(""))
                        .build();

                if (task.getId() != 0L && task.getName() != null) {
                    // avoid empty tasks
                    u.getTasks().add(task);
                }

            }
            return u;
        });

        return Optional.ofNullable(user);
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
        String sql = "SELECT u.id, u.username, u.email, u.phone, t.id AS task_id, t.name AS task_name, t.description AS task_description " +
                "FROM users u LEFT JOIN tasks t ON u.id = t.user_id ORDER BY u.id";
        return jdbc.query(sql, (ResultSetExtractor<List<User>>) rs -> {
            Map<Long, User> userMap = new HashMap<>();
            while (rs.next()) {
                Long userId = rs.getLong("id");
                User user = userMap.get(userId);
                if (user == null) {
                    user = User.builder()
                            .id(userId)
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .phone(rs.getString("phone"))
                            .tasks(new ArrayList<>())
                            .build();
                    userMap.put(userId, user);
                }
                long taskId = rs.getLong("task_id");
                if (taskId > 0) {
                    Task task = new Task();
                    task.setId(taskId);
                    task.setName(rs.getString("task_name"));
                    task.setDescription(rs.getString("task_description"));
                    user.getTasks().add(task);
                }
            }
            return new ArrayList<>(userMap.values());
        });
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

    private List<Task> findTasksByUserId(Long userId) {
        String query = "SELECT * FROM tasks WHERE user_id =?";
        return jdbc.query(query, BeanPropertyRowMapper.newInstance(Task.class), userId);
    }

}
