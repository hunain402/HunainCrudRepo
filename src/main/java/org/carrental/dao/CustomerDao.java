package org.carrental.dao;





import org.carrental.domain.Customer;
import org.carrental.mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CustomerDao extends BaseDao implements ICrud<Customer> {
    private final CustomerMapper customerMapper = new CustomerMapper();


    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_CUSTOMER);
            ps.setString(1,obj.getCustomerName());
            ps.setString(2,obj.getCustomerNumber());
            ps.setString(3,obj.getCustomerCnic());
            ps.setString(4,obj.getCustomerCnic());
            ps.setString(5,obj.getCustomerRefNumber());
            ResultSet rs = ps.executeQuery();
            customerMapper.resultSetToListObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        try {
           Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CUSTOMER);
            return customerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer GetById(Long id) {
        return null;
    }

    @Override
    public Customer Update(Customer obj, Long id) {
        return null;
    }

    @Override
    public void DeleteById(Long id) {

    }
}
