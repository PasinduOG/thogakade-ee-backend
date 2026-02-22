package edu.icet.ecom.service;

import edu.icet.ecom.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(String id);

    String updateCustomer(CustomerDto customerDto, String id);

    String deleteCustomer(String id);
}
