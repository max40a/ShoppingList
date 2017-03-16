package com.geekhub.max40a.repository.jdbc;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.Task;
import com.geekhub.max40a.model.enums.ItemStatus;
import com.geekhub.max40a.model.enums.TaskStatus;
import com.geekhub.max40a.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Task> getTaskByUser(Integer userId) {
        String sql = "SELECT * FROM tasks WHERE user_id = " + userId;
        List<Task> tasks = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Task task = new Task();

            task.setId((Integer) row.get("id"));
            task.setTitle((String) row.get("title"));
            task.setUserId((Integer) row.get("user_id"));
            task.setStatus(TaskStatus.valueOf((String) row.get("status")));

            tasks.add(task);
        }

        return tasks;
    }

    @Override
    public List<Item> getItemByTask(Integer taskId) {
        String sql = "SELECT * FROM items WHERE task_id = " + taskId;
        List<Item> items = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Item item = new Item();

            item.setId((Integer) row.get("id"));
            item.setDescription((String) row.get("description"));
            item.setStatus(ItemStatus.valueOf((String) row.get("status")));

            items.add(item);
        }

        return items;
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks VALUES (DEFAULT , ?, ?, ?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getUserId(), task.getStatus().getStatusToString().toUpperCase());
    }

    @Override
    public void deleteTask(Integer taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    @Override
    public void changeStatusOfTask(Integer taskId, TaskStatus status) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.getStatusToString().toUpperCase(), taskId);
    }
}