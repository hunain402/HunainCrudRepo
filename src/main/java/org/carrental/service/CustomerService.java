package org.carrental.service;
import org.carrental.dao.CustomerDao;
import org.carrental.domain.Customer;
import java.util.List;

public class CustomerService {
    CustomerDao customerDao = new CustomerDao();



    public String[][] searchByName(String name) {
        List<Customer> customerList = customerDao.getByName(name);
        return transformToJTable(customerList, 6);
    }

/**CUTOMER TABLE DATA*/

    public String[][] getAllCustomerForJTable() {
        List<Customer> customerList = customerDao.getAllExceptDelete();
        return transformToJTable(customerList, 6);
    }

     public Customer getCustomer (Long id) {
         Customer customer = customerDao.GetById(id);
         return customer;
     }

    //*******************************Transform Table *******************************************

    private String[][] transformToJTable(List<Customer> customerList, int columnSize) {

        String[][] tableData = new String[customerList.size()][columnSize];

        for (int i = 0; i < customerList.size(); i++) {

            tableData[i][0] = String.valueOf(customerList.get(i).getId());
            tableData[i][1] = customerList.get(i).getCustomername();
            tableData[i][2] = customerList.get(i).getCustomernumber();
            tableData[i][3] = customerList.get(i).getCustomercnic();
            tableData[i][4] = customerList.get(i).getCustomeraddress();
            tableData[i][5] = customerList.get(i).getCustomerrefnumber();

        }
        return tableData;
    }


    //*******************************************  Save    **********************************************************

    public void save(String name, String phone, String cnic, String address, String refPhoneNumber) {

        Customer customer = Customer.builder()

                .customernumber(phone)
                .customercnic(cnic)
                .customername(name)
                .customeraddress(address)
                .customerrefnumber(refPhoneNumber)
                .build();

        customerDao.insert(customer);
    }

    //**************************************************** Update from Ui *************************************************
    public void updateCustomer(String id, String name, String number, String cnic, String address, String refPhoneNumber) {
        Customer customer = Customer.builder()
                .customername(name)
                .customernumber(number)
                .customercnic(cnic)
                .customeraddress(address)
                .customerrefnumber(refPhoneNumber)
                .build();
        customerDao.Update(customer, Long.valueOf(Integer.valueOf(id)));

    }

    public void delete(String id) {

        customerDao.DeleteById(Long.valueOf(Integer.valueOf(id)));
    }

    public void softDelete(String id) {
        customerDao.updateDeleteById(Long.valueOf(Integer.valueOf(id)));
    }
}


