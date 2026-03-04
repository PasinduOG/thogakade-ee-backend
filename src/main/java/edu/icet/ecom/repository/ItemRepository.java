package edu.icet.ecom.repository;

import edu.icet.ecom.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository {
    boolean addItem(ItemEntity itemEntity);
    boolean updateItem(ItemEntity itemEntity);
    boolean deleteItem(String id);
    List<ItemEntity> getAll();
    Optional<ItemEntity> getItemById(String id);
    String getLastItemId();
}
