package edu.icet.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private String code;
    private String description;
    private String packSize;
    private Double price;
    private Integer qty;
}
