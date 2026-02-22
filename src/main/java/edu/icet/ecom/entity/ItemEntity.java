package edu.icet.ecom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemEntity {
    private String code;
    private String description;
    private String packSize;
    private Double price;
    private Integer qty;
}
