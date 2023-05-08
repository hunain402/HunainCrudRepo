package org.carrental.service;

import org.carrental.dao.CustomerDao;
import org.carrental.dao.OwnerDao;
import org.carrental.dao.SqlQueryConstant;
import org.carrental.domain.Customer;
import org.carrental.domain.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OwnerService {

    public void add(String name, String cnic,String number, String commission, String address) {
        Owner owner = Owner.builder()
                .ownername(name)
                .ownercnic(cnic)
                .ownernumber(number)
                .ownercommision(Float.valueOf(commission))
                .owneraddress(address)
                .build();
        new OwnerDao().insert(owner);
    }

    //save
    public void save(String name, String cnic,String number, String commission, String address) {
        Owner owner = Owner.builder()
                .ownername(name)
                .ownercnic(cnic)
                .ownernumber(number)
                .ownercommision(Float.valueOf(commission))
                .owneraddress(address)
                .build();
        new OwnerDao().insert(owner);
    }

    public String[][] getAll() {
        List<Owner> ownerList = new OwnerDao().getAll();
        return convertListTo2dArray(ownerList, 6);
    }

    public String[][] getByName(String name) {
        List<Owner> ownerList = new OwnerDao().getByName(name);
        return convertListTo2dArray(ownerList, 6);
    }

    private static String[][] convertListTo2dArray(List<Owner> ownerList, Integer columnSize) {
        String[][] data = new String[ownerList.size()][columnSize];
        for (int i = 0; i < ownerList.size(); i++) {
           // data[i][0] = ownerList.get(i).getOwnername();
            data[i][1] = ownerList.get(i).getOwnername();
            data[i][2] = String.valueOf(ownerList.get(i).getOwnercnic());
            data[i][3] = String.valueOf(ownerList.get(i).getOwnernumber());
            data[i][4] = ownerList.get(i).getOwneraddress();
            data[i][5] = String.valueOf(ownerList.get(i).getOwnercommision());
        }
        return data;
    }

    public String[] getOwnerIdAndNameForDropDown() {
        List<Owner> ownerList = new OwnerDao().getAll();
        String[] data = new String[ownerList.size()];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i] = String.valueOf((ownerList.get(i).getId()));
            data[i] += "  " + ownerList.get(i).getOwnername();
        }
        return data;
    }
}

