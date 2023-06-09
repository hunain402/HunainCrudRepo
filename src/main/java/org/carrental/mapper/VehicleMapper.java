package org.carrental.mapper;

import org.carrental.domain.Bookingdetails;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle> {

    @Override
    public List<Vehicle> resultSetToList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .vehiclemodel(rs.getLong("vehicle_model"))
                    .vehiclebrand(rs.getString("vehicle_brand"))
                    .vehiclecolour(rs.getString("vehicle_colour"))
                    .onwerid(rs.getLong("owner_id"))
                    .owner_name(rs.getString("owner_name"))

                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public List<Vehicle> resultSetToVList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    //  .vehiclemodel(rs.getLong("vehicle_model"))
                    //.vehiclebrand(rs.getString("vehicle_brand"))
                    // .vehiclecolour(rs.getString("vehicle_colour"))
                    //    .onwerid(rs.getLong("owner_id"))
                    //.owner_name(rs.getString("owner_name"))
                    .status(rs.getString("status"))

                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    @Override
    public Vehicle resultSetToListObject(ResultSet rs) throws SQLException {

        if (rs.next()) {
            return Vehicle.builder()
                    .id((long) rs.getInt("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .vehiclemodel(rs.getLong("vehicle_model"))
                    .vehiclebrand(rs.getString("vehicle_brand"))
                    .vehiclecolour(rs.getString("vehicle_colour"))
                    .onwerid(rs.getLong("owner_id"))
                    .owner_name(rs.getString("owner_name"))
                    .build();
        }
        return null;
    }

    public List<Vehicle> rsToList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .vehiclemodel(rs.getLong("vehicle_model"))
                    .vehiclebrand(rs.getString("vehicle_brand"))
                    .vehiclecolour(rs.getString("vehicle_colour"))
                    .onwerid(rs.getLong("owner_id"))
                    .owner_name(rs.getString("owner_name"))
                    .ownernumber(rs.getString("owner_number"))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public List<Vehicle> AVAiT(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }


    public List<Vehicle>annualTransform(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (rs.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public static List<Bookingdetails> yearlyRepoVehicle(ResultSet rs) throws SQLException {
        List<
                Bookingdetails> vehicleList = new ArrayList<>();

        while (rs.next()) {
            Bookingdetails vehicle = Bookingdetails.builder()
                  //  .id(rs.getLong("id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .owner_name(rs.getString("owner_name"))
                    .price(rs.getDouble("price"))
                    .owner_commision(rs.getLong("owner_commision"))
                    .bookingdate(rs.getDate("booking_date"))
                    .enddate(rs.getDate("end_date"))
                    .noOfDays(rs.getInt("noOfDays"))
                    .total_amount(rs.getDouble("total_amount"))
                    .commision(rs.getDouble("commision"))
                    .profit(rs.getDouble("profit"))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }
}
