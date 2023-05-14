package org.carrental.service;

import org.carrental.dao.OwnerDao;
import org.carrental.dao.VehicleDao;
import org.carrental.domain.Bookingdetails;

import java.util.List;

public class ReportService {


    public String[][] yearlyRepOwner(Integer date, String name) {
        List<Bookingdetails> ownerList =new  OwnerDao().getOwners(date, name);
        String[][] data = new String[ownerList.size()][6];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i][0] = ownerList.get(i).getOwner_name();
            data[i][1] = ownerList.get(i).getOwner_commision() + "%";
            data[i][2] = ownerList.get(i).getVehiclename();
            data[i][3] = String.valueOf(ownerList.get(i).getBookingdate());
            data[i][4] = String.valueOf(ownerList.get(i).getEnddate());
            data[i][5] = "Rs." + ownerList.get(i).getCommision();
        }
        return data;
    }

    public Double totalCommssionOfOwner(Integer date, String name) {
        List<Bookingdetails> ownerList =  new OwnerDao().getOwners(date, name);
        Double totalCommission = 0.0;
        for (int i = 0; i < ownerList.size(); i++) {
            totalCommission += ownerList.get(i).getCommision();
        }
        return totalCommission;
    }

    public String[][] yearlyRepVehicle(Integer date, String name) {
        List<Bookingdetails> vehiclelist =new VehicleDao().yearRepVehicle(date, name);
        String[][] data = new String[vehiclelist.size()][10];
        for (int i = 0; i < vehiclelist.size(); i++) {
            data[i][0] = vehiclelist.get(i).getVehiclename();
            data[i][1] = vehiclelist.get(i).getOwner_name();
            data[i][2] = String.valueOf(vehiclelist.get(i).getPrice());
            data[i][3] = String.valueOf(vehiclelist.get(i).getOwner_commision());
            data[i][4] = String.valueOf(vehiclelist.get(i).getBookingdate());
            data[i][5] = String.valueOf(vehiclelist.get(i).getEnddate());
            data[i][6] = String.valueOf(vehiclelist.get(i).getNoOfDays());
            data[i][7] = String.valueOf(vehiclelist.get(i).getTotal_amount());
            data[i][8] = String.valueOf(vehiclelist.get(i).getCommision());
            data[i][9] = String.valueOf(vehiclelist.get(i).getProfit());

        }
        return data;
    }
    public Double totalprofitOfVehice(Integer date, String name) {
        List<Bookingdetails> vehiclelist =  new VehicleDao().yearRepVehicle(date, name);
        Double totalCommission = 0.0;
        for (int i = 0; i < vehiclelist.size(); i++) {
            totalCommission += vehiclelist.get(i).getProfit();
        }
        return totalCommission;
    }
}
