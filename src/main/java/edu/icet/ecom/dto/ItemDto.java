package edu.icet.ecom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    @NotBlank(message = "Item ID required")
    @Pattern(regexp = "^P\\d{3}$", message = "Invalid Item Code format. Expected format: P000")
    private String code;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Pack size is required")
    private String packSize;

    @Positive(message = "Price is required and cannot be negative")
    private Double price;

    @Positive(message = "Quantity is required and cannot be negative")
    private Integer qty;
}
