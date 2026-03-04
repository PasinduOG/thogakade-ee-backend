package edu.icet.ecom.controller;

import edu.icet.ecom.dto.CustomerDto;
import edu.icet.ecom.service.CustomerService;
import io.github.og4dev.annotation.AutoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin
@AutoResponse
@SuppressWarnings("unused")
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    List<CustomerDto> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    CustomerDto getCustomerById(@PathVariable String id) {
        return service.getCustomerById(id);
    }

    @GetMapping("/get-customer-id")
    String getGeneratedId(){
        return service.generateCustomerId();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AutoResponse(message = "Customer created successfully")
    String createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return service.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @AutoResponse(message = "Customer updated successfully")
    String updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable String id) {
        return service.updateCustomer(customerDto, id);
    }

    @DeleteMapping("/{id}")
    @AutoResponse(message = "Customer deleted successfully")
    String deleteCustomer(@PathVariable String id) {
        return service.deleteCustomer(id);
    }
}
