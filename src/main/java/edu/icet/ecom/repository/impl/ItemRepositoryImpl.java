package edu.icet.ecom.repository.impl;

import edu.icet.ecom.entity.ItemEntity;
import edu.icet.ecom.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ItemRepositoryImpl implements ItemRepository {
    private final JdbcTemplate template;

    @Override
    public boolean addItem(ItemEntity itemEntity) {
        return template.update("INSERT INTO item VALUES (?,?,?,?,?)",
                itemEntity.getCode(),
                itemEntity.getDescription(),
                itemEntity.getPackSize(),
                itemEntity.getPrice(),
                itemEntity.getQty()) > 0;
    }

    @Override
    public boolean updateItem(ItemEntity itemEntity) {
        return template.update("UPDATE item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE code=?",
                itemEntity.getDescription(),
                itemEntity.getPackSize(),
                itemEntity.getPrice(),
                itemEntity.getQty(),
                itemEntity.getCode()) > 0;
    }

    @Override
    public boolean deleteItem(String id) {
        return template.update("DELETE FROM item WHERE code=?", id) > 0;
    }

    @Override
    public List<ItemEntity> getAll() {
        return template.query("SELECT * FROM item", (rs, rowNum) -> {
            ItemEntity entity = new ItemEntity();
            entity.setCode(rs.getString("ItemCode"));
            entity.setDescription(rs.getString("Description"));
            entity.setPackSize(rs.getString("PackSize"));
            entity.setPrice(rs.getDouble("UnitPrice"));
            entity.setQty(rs.getInt("QtyOnHand"));
            return entity;
        });
    }

    @Override
    public Optional<ItemEntity> getItemById(String id) {
        try {
            return Optional.ofNullable(template.queryForObject("SELECT * FROM item WHERE code=?", (rs, rowNum) -> {
                ItemEntity entity = new ItemEntity();
                entity.setCode(rs.getString("ItemCode"));
                entity.setDescription(rs.getString("Description"));
                entity.setPackSize(rs.getString("PackSize"));
                entity.setPrice(rs.getDouble("UnitPrice"));
                entity.setQty(rs.getInt("QtyOnHand"));
                return entity;
            }));
        } catch (RuntimeException ex) {
            return Optional.empty();
        }
    }

    @Override
    public String getLastItemId() {
        try {
            return template.queryForObject("SELECT code FROM item ORDER BY code DESC LIMIT 1",
                    (rs, rowNum) -> rs.getString("code"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
