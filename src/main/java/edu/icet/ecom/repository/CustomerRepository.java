package edu.icet.ecom.repository;

import edu.icet.ecom.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    boolean addCustomer(CustomerEntity customerEntity);
    boolean updateCustomer(CustomerEntity customerEntity);
    boolean deleteCustomer(String id);
    List<CustomerEntity> getAll();
    Optional<CustomerEntity> getCustomerById(String id);
}
