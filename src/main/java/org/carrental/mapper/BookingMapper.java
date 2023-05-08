package org.carrental.mapper;

import org.carrental.domain.Booking;
import org.carrental.domain.Bookingdetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking> {

    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String STATUS = "status";
    public static final String DATE = "booking_date";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String VEHICLE_ID = "vehicle_id";
    public static final String C_ID = "c.id";
    public static final String V_ID = "v.id";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String VEHICLE_NAME = "vehicle_name";
    public static final String END_DATE = "end_date";

    @Override
    public List<Booking> resultSetToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (rs.next()) {
           Booking booking = Booking.builder()
                   .id(rs.getLong(ID))
                   .price(rs.getDouble(PRICE))
                   .status(rs.getString(STATUS))
                   .booking_date(rs.getDate(DATE))
                   .customerid(rs.getLong(CUSTOMER_ID))
                   .Vehicleid(rs.getLong(VEHICLE_ID))
                    .build();
            bookingList.add(booking);
        }
        return  bookingList;
    }


    @Override
    public Booking resultSetToListObject(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return Booking.builder()
                    .id((long) rs.getInt(ID))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .booking_date(rs.getDate(DATE))
                    .customerid(rs.getLong(CUSTOMER_ID))
                    .Vehicleid(rs.getLong(VEHICLE_ID))
                    .build();
        }
        return null;
    }

    public Bookingdetails rsToObj(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return Bookingdetails.builder()
                    .id((long) rs.getInt(ID))
                    .price(rs.getDouble(PRICE))
                    .status(rs.getString(STATUS))
                    .bookingdate(rs.getDate(DATE))
                    .enddate(rs.getDate(END_DATE))
                    .customerid(rs.getLong(CUSTOMER_ID))
                    .vehicleid(rs.getLong(VEHICLE_ID))
                    .customername(rs.getString(VEHICLE_NAME))
                    .customername(rs.getString(CUSTOMER_NAME))
                    .build();
        }
        return null;
    }


    ////////////////////////////////////////////////////////////////////////////////

    public List<Bookingdetails> rsToList(ResultSet rs) throws SQLException {
        List<Bookingdetails> bookingdetails = new ArrayList<>();
        while (rs.next()) {
            Bookingdetails b  =  Bookingdetails.builder()
                    .id(rs.getLong(ID))
                    .price(rs.getDouble(PRICE))
                  //  .price(rs.getDouble("total_amount"))
                    .status(rs.getString(STATUS))
                    .bookingdate(rs.getDate(DATE))
                    .enddate(rs.getDate(END_DATE))
                    .customerid(rs.getLong(C_ID))
                    .vehicleid(rs.getLong(V_ID))
                    //.vehicleid(rs.getLong("vehicle_id"))
                    .customername(rs.getString(CUSTOMER_NAME))
                    .vehiclename(rs.getString(VEHICLE_NAME))

                    .build();
            bookingdetails.add(b);
        }
        return bookingdetails;
    }
////////////////////////////////////
public List<Bookingdetails> Test(ResultSet rs) throws SQLException {
    List<Bookingdetails> bookingdetails = new ArrayList<>();
    while (rs.next()) {
        Bookingdetails b  =  Bookingdetails.builder()
                .id(rs.getLong(ID))
                .price(rs.getDouble(PRICE))
                //  .price(rs.getDouble("total_amount"))
                .status(rs.getString(STATUS))
                .bookingdate(rs.getDate(DATE))
                   .enddate(rs.getDate(END_DATE))

                //.vehicleid(rs.getLong("vehicle_id"))
                .customername(rs.getString(CUSTOMER_NAME))
                .vehiclename(rs.getString(VEHICLE_NAME))
//                .noOfDays(rs.getDouble("perdayPrice"))
                .noOfDays(rs.getInt("noOfDays"))
                .total_amount(rs.getDouble("total_amount"))

                .build();
        bookingdetails.add(b);
    }
    return bookingdetails;
}


    ///////////////////////

    public List<Bookingdetails> rsToAmountList(ResultSet rs) throws SQLException {
        List<Bookingdetails> bookingdetails = new ArrayList<>();
        while (rs.next()) {
            Bookingdetails b  =  Bookingdetails.builder()
                    .vehicleid(rs.getLong("vehicle_id"))
                    .vehiclename(rs.getString("vehicle_name"))
                    .build();
            bookingdetails.add(b);
        }
        return bookingdetails;
    }

    public List<Bookingdetails> rsToMaxCommisionList(ResultSet rs) throws SQLException {
        List<Bookingdetails> bookingdetails = new ArrayList<>();
        while (rs.next()) {
            Bookingdetails b  =  Bookingdetails.builder()
                    .owner_id(rs.getLong("owner_id"))
                    .owner_name(rs.getString("owner_name"))
                    .build();
            bookingdetails.add(b);
        }
        return bookingdetails;
    }

    public List<Bookingdetails> ResultSetToListOfCommission(ResultSet rs) throws SQLException {
        List<Bookingdetails> commissionList = new ArrayList<>();
        while (rs.next()) {
            Bookingdetails booking = Bookingdetails.builder()
                    .commision(rs.getDouble("commision"))
                    .build();
            commissionList.add(booking);
        }
        return commissionList;
    }

    public List<Bookingdetails> ResultSetToListOfCommissionOwner(ResultSet rs) throws SQLException {
        List<Bookingdetails> commissionList = new ArrayList<>();
        while (rs.next()) {
            Bookingdetails booking = Bookingdetails.builder()
                   // .owner_id(Long.valueOf("v.owner_id"))
                    .owner_name(rs.getString("owner_name"))
                    .commision(rs.getDouble("commision"))
                    .owner_commision(rs.getLong("owner_commision"))
                    .build();
            commissionList.add(booking);
        }
        return commissionList;
    }
}
