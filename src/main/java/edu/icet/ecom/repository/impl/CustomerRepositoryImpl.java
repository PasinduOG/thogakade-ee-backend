package edu.icet.ecom.repository.impl;

import edu.icet.ecom.entity.CustomerEntity;
import edu.icet.ecom.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
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
            entity.setId(rs.getString(1));
            entity.setTitle(rs.getString(2));
            entity.setName(rs.getString(3));
            entity.setDob(rs.getDate(4).toLocalDate());
            entity.setSalary(rs.getDouble(5));
            entity.setAddress(rs.getString(6));
            entity.setCity(rs.getString(7));
            entity.setProvince(rs.getString(8));
            entity.setPostalCode(rs.getString(9));
            return entity;
        });
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(String id) {
        try{
            String sql = "SELECT * FROM customer WHERE CustID=?";
            CustomerEntity customerEntity = template.queryForObject(sql, (rs, rowNum) -> {
                CustomerEntity entity = new CustomerEntity();
                entity.setId(rs.getString(1));
                entity.setTitle(rs.getString(2));
                entity.setName(rs.getString(3));
                entity.setDob(rs.getDate(4).toLocalDate());
                entity.setSalary(rs.getDouble(5));
                entity.setAddress(rs.getString(6));
                entity.setCity(rs.getString(7));
                entity.setProvince(rs.getString(8));
                entity.setPostalCode(rs.getString(9));
                return entity;
            }, id);
            return Optional.ofNullable(customerEntity);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
