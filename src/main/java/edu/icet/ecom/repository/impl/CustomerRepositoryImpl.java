package edu.icet.ecom.repository.impl;

import edu.icet.ecom.entity.CustomerEntity;
import edu.icet.ecom.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class CustomerRepositoryImpl implements CustomerRepository {
    private final JdbcTemplate template;

    @Override
    public boolean addCustomer(CustomerEntity customerEntity) {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?)";
        return template.update(sql, customerEntity.getId(),
                customerEntity.getTitle(),
                customerEntity.getName(),
                customerEntity.getDob(),
                customerEntity.getSalary(),
                customerEntity.getAddress(),
                customerEntity.getCity(),
                customerEntity.getProvince(),
                customerEntity.getPostalCode()) > 0;
    }

    @Override
    public boolean updateCustomer(CustomerEntity customerEntity) {
        String sql = "UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, " +
                "PostalCode=? WHERE CustID=?";
        return template.update(sql, customerEntity.getTitle(),
                customerEntity.getName(),
                customerEntity.getDob(),
                customerEntity.getSalary(),
                customerEntity.getAddress(),
                customerEntity.getCity(),
                customerEntity.getProvince(),
                customerEntity.getPostalCode(),
                customerEntity.getId()
                ) > 0;
    }

    @Override
    public boolean deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE CustID=?";
        return template.update(sql, id) > 0;
    }

    @Override
    public List<CustomerEntity> getAll() {
        String sql = "SELECT * FROM customer";
        return template.query(sql, (rs, rowNum) -> {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(rs.getString("CustID"));
            entity.setTitle(rs.getString("CustTitle"));
            entity.setName(rs.getString("CustName"));
            entity.setDob(rs.getDate("DOB"));
            entity.setSalary(rs.getDouble("salary"));
            entity.setAddress(rs.getString("CustAddress"));
            entity.setCity(rs.getString("City"));
            entity.setProvince(rs.getString("Province"));
            entity.setPostalCode(rs.getString("PostalCode"));
            return entity;
        });
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(String id) {
        try {
            String sql = "SELECT * FROM customer WHERE CustID=?";
            CustomerEntity customerEntity = template.queryForObject(sql, (rs, rowNum) -> {
                CustomerEntity entity = new CustomerEntity();
                entity.setId(rs.getString("CustID"));
                entity.setTitle(rs.getString("CustTitle"));
                entity.setName(rs.getString("CustName"));
                entity.setDob(rs.getDate("DOB"));
                entity.setSalary(rs.getDouble("salary"));
                entity.setAddress(rs.getString("CustAddress"));
                entity.setCity(rs.getString("City"));
                entity.setProvince(rs.getString("Province"));
                entity.setPostalCode(rs.getString("PostalCode"));
                return entity;
            }, id);
            return Optional.ofNullable(customerEntity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public String getLastCustomerId() {
        String sql = "SELECT CustID from Customer ORDER BY CustID DESC LIMIT 1";
        try {
            return template.queryForObject(sql, (rs, rowNum) -> rs.getString("CustID"));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
