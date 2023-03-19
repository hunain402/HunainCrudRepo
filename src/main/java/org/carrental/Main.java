package org.carrental;

import javafx.util.Builder;
import org.carrental.UI.LoginUI;
import org.carrental.dao.CustomerDao;
import org.carrental.domain.Customer;

public class Main {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();

//        Customer newCus = Customer.builder()
//                .customerName("yahya")
//                .customerNumber("030020303030")
//                .customerCnic("4402020202")
//                .customerAddress("streetf")
//                .customerRefNumber("sgdgcsydg")
//                .build();
//
//        customerDao.insert(newCus);
        customerDao.getAll().forEach(System.out::println);

       }
}