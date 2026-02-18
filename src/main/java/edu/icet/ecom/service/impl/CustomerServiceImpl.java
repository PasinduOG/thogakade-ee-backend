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
    public void createCustomer(CustomerDto customerDto) {
        repository.addCustomer(mapper.map(customerDto, CustomerEntity.class));
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
    public void updateCustomer(CustomerDto customerDto, String id) {
        if (repository.getCustomerById(id).isEmpty())
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        customerDto.setId(id);
        repository.updateCustomer(mapper.map(customerDto, CustomerEntity.class));
    }

    @Override
    public void deleteCustomer(String id) {
        if (repository.getCustomerById(id).isEmpty())
            throw new ResourceNotFoundException("Customer not found with ID: " + id);
        repository.deleteCustomer(id);

    }
}
