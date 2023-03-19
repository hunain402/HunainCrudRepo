package org.carrental.dao;

public class SqlQueryConstant {
    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String INSERT_INTO_CUSTOMER = "insert into customer (customer_name,customer_cnic,customer_Cnic,customer_address,customer_refNumber) " +
            "values(?,?,?,?,?)";
   // public final static String GET_ALL_OWNER = "select * from owner";
}
