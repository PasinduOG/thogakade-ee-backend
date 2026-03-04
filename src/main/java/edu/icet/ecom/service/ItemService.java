package edu.icet.ecom.service;

import edu.icet.ecom.dto.ItemDto;

import java.util.List;

public interface ItemService {
    String generateItemId();

    String createItem(ItemDto itemDto);

    List<ItemDto> getAllItems();

    ItemDto getItemById(String id);

    String updateItem(ItemDto itemDto, String id);

    String deleteItem(String id);
}
