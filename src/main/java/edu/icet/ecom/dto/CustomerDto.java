package edu.icet.ecom.dto;

import io.github.og4dev.annotation.AutoTrim;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {
    @NotBlank(message = "Customer ID required")
    @Pattern(regexp = "^C\\d{3}$", message = "Invalid Customer ID format. Expected format: C000")
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @AutoTrim
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of Birth must be a past date")
    private LocalDate dob;

    @Positive(message = "Salary is required and cannot be negative")
    private Double salary;

    @AutoTrim
    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    @Pattern(regexp = "^\\d{5}$", message = "Postal code is required and must be exactly 5 digits")
    private String postalCode;
}
