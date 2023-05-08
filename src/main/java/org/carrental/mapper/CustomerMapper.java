package org.carrental.mapper;
import org.carrental.domain.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements  IMapper {
    public static final String STATUS = "status";
    private String ID="id";
    private String  CUSTOMER_NAME =  "customer_name";
    private String CUSTOMER_NUMBER = "customer_number";
    private String CUSTOMER_CNIC =  "customer_cnic";
    private String  CUSTOMER_ADDRESS = "customer_address";
    private String  CUSTOMER_REF_NUMBER = "customer_ref_number";


    @Override
    public List<Customer>  resultSetToList(ResultSet rs) throws SQLException {

        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer customer = Customer.builder()
                    .id(rs.getLong(ID))
                    .customername(rs.getString(CUSTOMER_NAME))
                    .customernumber(rs.getString(CUSTOMER_NUMBER))
                    .customercnic(rs.getString(CUSTOMER_CNIC))
                    .customeraddress(rs.getString(CUSTOMER_ADDRESS))
                    .customerrefnumber(rs.getString(CUSTOMER_REF_NUMBER))
                    .status(rs.getString(STATUS))
                    .build();
            customerList.add(customer);
        }
         return  customerList;
    }

    @Override
    public Customer resultSetToListObject(ResultSet rs) throws SQLException {
if(rs.next()) {
    Customer convertintolist= Customer.builder()
            .id((long) rs.getInt(ID))
            .customername(rs.getString(CUSTOMER_NAME))
            .customernumber(rs.getString(CUSTOMER_NUMBER))
            .customercnic(rs.getString(CUSTOMER_CNIC))
            .customeraddress(rs.getString(CUSTOMER_ADDRESS))
            .customerrefnumber(rs.getString(CUSTOMER_REF_NUMBER))
            .build();
    return convertintolist;
}
          return null;
}
}
