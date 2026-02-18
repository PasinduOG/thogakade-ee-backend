package edu.icet.ecom.repository;

import edu.icet.ecom.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(CustomerEntity customerEntity);
    boolean updateCustomer(CustomerEntity customerEntity);
    boolean deleteCustomer(String id);
    List<CustomerEntity> getAll();
    CustomerEntity getCustomerById(String id);
}
