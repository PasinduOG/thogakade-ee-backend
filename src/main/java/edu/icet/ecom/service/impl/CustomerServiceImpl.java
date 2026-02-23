package edu.icet.ecom.service.impl;

import edu.icet.ecom.dto.CustomerDto;
import edu.icet.ecom.entity.CustomerEntity;
import edu.icet.ecom.exception.ResourceNotFoundException;
import edu.icet.ecom.repository.CustomerRepository;
import edu.icet.ecom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final ModelMapper mapper;

    @Override
    public String generateCustomerId() {
        String lastCustomerId = repository.getLastCustomerId();
        if (lastCustomerId == null) return "C001";
        int lastIndex = Integer.parseInt(lastCustomerId.substring(1));
        return String.format("C%03d", lastIndex + 1);
    }

    @Override
    public String createCustomer(CustomerDto customerDto) {
        boolean b = repository.addCustomer(mapper.map(customerDto, CustomerEntity.class));
        if (!b) return "Failed to add customer";
        return "Customer created";
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return repository.getAll().stream()
                .map(entity -> mapper.map(entity, CustomerDto.class)).toList();
    }

    @Override
    public CustomerDto getCustomerById(String id) {
        return mapper.map(repository.getCustomerById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer not found with ID: " + id)), CustomerDto.class);
    }

    @Override
    public String updateCustomer(CustomerDto customerDto, String id) {
        if (repository.getCustomerById(id).isEmpty())
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        customerDto.setId(id);
        boolean b = repository.updateCustomer(mapper.map(customerDto, CustomerEntity.class));
        if (!b) return "Failed to update customer";
        return "Customer updated";
    }

    @Override
    public String deleteCustomer(String id) {
        if (repository.getCustomerById(id).isEmpty())
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        boolean b = repository.deleteCustomer(id);
        if (!b) return "Failed to remove customer";
        return "Customer removed";
    }
}
