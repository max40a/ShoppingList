package com.geekhub.max40a.repository.jdbc;

import com.geekhub.max40a.model.User;
import com.geekhub.max40a.repository.TaskRepository;
import com.geekhub.max40a.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImp implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private TaskRepository taskRepository;

    @Autowired
    public UserRepositoryImp(DataSource dataSource, TaskRepository taskRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            User user = new User();

            user.setId((Integer) row.get("id"));
            user.setName((String) row.get("name"));
            user.setEmail((String) row.get("email"));
            user.setPassword((String) row.get("password"));

            users.add(user);
        }

        users.forEach(this::findUserTasks);

        return users;
    }

    @Override
    public User findById(Integer id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            User rowedUser = new User();
            rowedUser.setId(rs.getInt("id"));
            rowedUser.setName(rs.getString("name"));
            rowedUser.setEmail(rs.getString("email"));
            rowedUser.setPassword(rs.getString("password"));
            return rowedUser;
        });

        findUserTasks(user);

        return user;
    }

    private User findUserTasks(User user) {
        user.setTasks(taskRepository.getTaskByUser(user.getId()));
        return user;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        if (user.isNew()) {
            String sql = "INSERT INTO users VALUES(DEFAULT, ?, ?, ?);";
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
        } else {
            String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = " + user.getId();
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword());
        }
    }
}