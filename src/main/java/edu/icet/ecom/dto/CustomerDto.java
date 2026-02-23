package edu.icet.ecom.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerDto {
    @NotBlank(message = "Customer ID required")
    @Pattern(regexp = "^C\\d{3}$", message = "Invalid Customer ID format. Expected format: C000")
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of Birth must be a past date")
    private Date dob;

    @NotNull(message = "Salary is required")
    @PositiveOrZero(message = "Salary cannot be negative")
    private Double salary;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^\\d{5}$", message = "Postal code must be exactly 5 digits")
    private String postalCode;
}
