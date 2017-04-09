package com.geekhub.max40a.repository.jdbc;

import com.geekhub.max40a.model.Item;
import com.geekhub.max40a.model.enums.ItemStatus;
import com.geekhub.max40a.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addItemInTask(Item item, Integer taskId) {
        String sql = "INSERT INTO items VALUES (DEFAULT, ?, ?, ?)";
        jdbcTemplate.update(sql, taskId, item.getDescription(), item.getStatus().getStatusToString().toUpperCase());
    }

    @Override
    public void deleteItemInTask(Integer itemId) {
        String sql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(sql, itemId);
    }

    @Override
    public void changeItemStatus(Integer itemId, ItemStatus status) {
        String sql = "UPDATE items SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.getStatusToString().toUpperCase(), itemId);
    }
}