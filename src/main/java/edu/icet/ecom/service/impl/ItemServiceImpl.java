package edu.icet.ecom.service.impl;

import edu.icet.ecom.dto.ItemDto;
import edu.icet.ecom.entity.ItemEntity;
import edu.icet.ecom.repository.ItemRepository;
import edu.icet.ecom.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final ModelMapper mapper;

    @Override
    public String generateItemId() {
        String lastItemId = repository.getLastItemId();
        if (lastItemId == null) return "P001";
        int lastIndex = Integer.parseInt(lastItemId.substring(1));
        return String.format("P%03d", lastIndex + 1);
    }

    @Override
    public String createItem(ItemDto itemDto) {
        boolean b = repository.addItem(mapper.map(itemDto, ItemEntity.class));
        if (!b) return "Failed to add item";
        return "Item created";
    }

    @Override
    public List<ItemDto> getAllItems() {
        return repository.getAll().stream()
                .map(entity -> mapper.map(entity, ItemDto.class)).toList();
    }

    @Override
    public ItemDto getItemById(String id) {
        return null;
    }

    @Override
    public String updateItem(ItemDto itemDto, String id) {
        return "";
    }

    @Override
    public String deleteItem(String id) {
        return "";
    }
}
