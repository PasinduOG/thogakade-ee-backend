package edu.icet.ecom.controller;

import edu.icet.ecom.dto.CustomerDto;
import edu.icet.ecom.service.CustomerService;
import io.github.og4dev.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin
@SuppressWarnings("unused")
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    ResponseEntity<@NotNull ApiResponse<List<CustomerDto>>> getAllCustomers() {
        return ApiResponse.success(service.getAllCustomers().size() + " found", service.getAllCustomers());
    }

    @GetMapping("/{id}")
    ResponseEntity<@NotNull ApiResponse<CustomerDto>> getCustomerById(@PathVariable String id) {
        return ApiResponse.success("Customer found", service.getCustomerById(id));
    }

    @GetMapping("/get-customer-id")
    String getGeneratedId(){
        return service.generateCustomerId();
    }

    @PostMapping
    ResponseEntity<@NotNull ApiResponse<Void>> createCustomer(@RequestBody CustomerDto customerDto) {
        return ApiResponse.status(service.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<@NotNull ApiResponse<Void>> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable String id) {
        return ApiResponse.success(service.updateCustomer(customerDto, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<@NotNull ApiResponse<Void>> deleteCustomer(@PathVariable String id) {
        return ApiResponse.success(service.deleteCustomer(id));
    }
}
