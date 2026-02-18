package edu.icet.ecom.service;

import edu.icet.ecom.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(String id);

    void updateCustomer(CustomerDto customerDto, String id);

    void deleteCustomer(String id);
}
