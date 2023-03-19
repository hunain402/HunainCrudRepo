package org.carrental.mapper;

import org.carrental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements  IMapper {
    private String ID="id";
    private String  CUSTOMER_NAME =  "customer_Name";
    private String CUSTOMER_NUMBER = "customer_Number";
    private String CUSTOMER_CNIC =  "customer_Cnic";
    private String  CUSTOMER_ADDRESS = "customer_Address";
    private String  CUSTOMER_REF_NUMBER = "customer_Ref_Number";


    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {

        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = Customer.builder()
                    .id((long) rs.getInt(ID))
                    .customerName(rs.getString(CUSTOMER_NAME))
                    .customerName(rs.getString(CUSTOMER_NUMBER))
                    .customerName(rs.getString(CUSTOMER_CNIC))
                    .customerName(rs.getString(CUSTOMER_ADDRESS))
                    .customerName(rs.getString(CUSTOMER_REF_NUMBER))
                    .build();
            customerList.add(customer);
        }
         return  customerList;
    }

    @Override
    public Customer resultSetToListObject(ResultSet rs) throws SQLException {
        return Customer.builder()
                .id((long) rs.getInt(ID))
                .customerName(rs.getString(CUSTOMER_NAME))
                .customerName(rs.getString(CUSTOMER_NUMBER))
                .customerName(rs.getString(CUSTOMER_CNIC))
                .customerName(rs.getString(CUSTOMER_ADDRESS))
                .customerName(rs.getString(CUSTOMER_REF_NUMBER))
                .build();


    }
}
