package org.carrental.dao;

import com.toedter.calendar.JDateChooser;
import org.carrental.domain.Booking;
import org.carrental.domain.Bookingdetails;
import org.carrental.mapper.BookingMapper;

import java.sql.*;
import java.util.List;

//                                   THIS CLASS PERFORM CRUD OPERATIONS OF DATABASE TABLE(BOOKING TABLE)
//                                   EXTENDS WITH BASE DAO CLASS FOR CONNECTION WITH DATA BASE AND IMPLEMENT WITH I CRUD
//
    public class BookingDao extends BaseDao implements ICrud<Booking> {
    private final BookingMapper bookingMapper = new BookingMapper();


    //********************************************** INSERT INTO BOOKING ***********************************************
    @Override
    public void insert(Booking obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_BOOKING);
            ps.setDouble(1, obj.getPrice());
           // ps.setString(5, obj.getStatus());
            ps.setDate(3, (Date) obj.getBooking_date());
            ps.setLong(4, obj.getCustomerid());
            ps.setLong(2, obj.getVehicleid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //*************************************  GET ALL BOOKING  *************************************************************
    @Override
    public List<Booking> getAll() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_BOOKING);
            return bookingMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //                                                   GET ALL BOOKING DETAILS
    public List<Bookingdetails> getAllBookingDetails(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_BOOKING_DETAIL);
            return bookingMapper.rsToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //*************************************  GET BOOKING BY ID  ********************************************************
    @Override
    public Booking GetById(Long id) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_BOOKING_BY_ID);
            ps.setLong(1, id.longValue());
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToListObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //*************************************  BOOKING INTO BOOKING  *****************************************************
    @Override
    public void Update(Booking obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_BOOKING_BY_ID);
            ps.setDouble(1,obj.getPrice());
            ps.setLong(2,id.longValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //*************************************  DELETE BOOKING BY ID  *****************************************************
    @Override
    public void DeleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.DELETE_BOOKING_BY_ID);
            ps.setLong(1, id.longValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //    *************************************  GET ALL BOOKING BY PRICE  *********************************************
//                                                ADDITIONAL METHOD

    public List<Booking> getByPrice(double num) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from booking where price like '%" + num + "%'");
            ResultSet rs = ps.executeQuery();
            return bookingMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    public List<Booking> getAllCid() {
//
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CID);
//            return bookingMapper.resultSetToList(rs);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
    //   }
   // }

    public List<Bookingdetails> getAll30DaysBookingDetails(JDateChooser startDateChooser, JDateChooser endDateChooser) {
        Date startDate = new Date(startDateChooser.getDate().getTime());
        Date endDate = new Date(endDateChooser.getDate().getTime());


        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_ALL_30DAYS_BOOKING);
            ps.setDate(1, startDate);
            ps.setDate(2,endDate);

            ResultSet rs = ps.executeQuery();
            return bookingMapper.Test(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//                           GET SUM OF ALL INDIVIDUAL BOOKING
public List<Bookingdetails> mostFrequentCar(JDateChooser startDateChooser, JDateChooser endDateChooser){
    try {
        Date startDate = new Date(startDateChooser.getDate().getTime());
        Date endDate = new Date(endDateChooser.getDate().getTime());

        PreparedStatement PS = conn.prepareStatement(SqlQueryConstant.MOST_FREQUENT_CAR);
        PS.setDate(1, startDate);
        PS.setDate(2, endDate);

        ResultSet rs= PS.executeQuery();
        return bookingMapper.rsToAmountList(rs);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public List<Bookingdetails> leastFrequentCar(JDateChooser startDateChooser, JDateChooser endDateChooser){
        try {
            Date startDate = new Date(startDateChooser.getDate().getTime());
            Date endDate = new Date(endDateChooser.getDate().getTime());

            PreparedStatement PS = conn.prepareStatement(SqlQueryConstant.LEAST_FREQUENT_CAR);
            PS.setDate(1, startDate);
            PS.setDate(2, endDate);
            PS.setDate(3, startDate);
            PS.setDate(4, endDate);

            ResultSet rs= PS.executeQuery();
            return bookingMapper.rsToAmountList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Bookingdetails> highestRevenueCarOfTheMonth(JDateChooser startDateChooser, JDateChooser endDateChooser){
        try {
            Date startDate = new Date(startDateChooser.getDate().getTime());
            Date endDate = new Date(endDateChooser.getDate().getTime());

            PreparedStatement PS = conn.prepareStatement(SqlQueryConstant.GET_HIGHEST_REVENUE_CAR_OF_THE_MONTH);
            PS.setDate(1, startDate);
            PS.setDate(2, endDate);

            ResultSet rs= PS.executeQuery();
            return bookingMapper.rsToAmountList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bookingdetails> maxCommisionOfTheMonth(JDateChooser startDateChooser, JDateChooser endDateChooser){
        try {
            Date startDate = new Date(startDateChooser.getDate().getTime());
            Date endDate = new Date(endDateChooser.getDate().getTime());

            PreparedStatement PS = conn.prepareStatement(SqlQueryConstant.MAX_COMMISON_OF_THE_MONTH);
            PS.setDate(1, startDate);
            PS.setDate(2, endDate);

            ResultSet rs= PS.executeQuery();
            return bookingMapper.rsToMaxCommisionList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




//                                          Complete booking method

public  void completeBooking(String id,JDateChooser endDateChooser){

    try {

        Date endDate = new Date(endDateChooser.getDate().getTime());

        PreparedStatement PS = conn.prepareStatement("UPDATE booking\n" +
                "SET status = 'completed', end_date = ?\n" +
                "WHERE id = ?");
        PS.setDate(1,endDate);
        PS.setInt(2, Integer.parseInt(id));
         PS.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }



}

    public List<Bookingdetails> totalCommission(JDateChooser startDateChooser, JDateChooser endDateChooser) {

        try {

            Date startDate = new Date(startDateChooser.getDate().getTime());
            Date endDate = new Date(endDateChooser.getDate().getTime());

            PreparedStatement statement = conn.prepareStatement(SqlQueryConstant.GET_ALL_COMMISION);
            statement.setDate(1,startDate );
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            return bookingMapper.ResultSetToListOfCommission(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bookingdetails>getEveryOwnerCommision(JDateChooser startDateChooser,JDateChooser endDateChooser) {
        try {

            Date startDate = new Date(startDateChooser.getDate().getTime());
            Date endDate = new Date(endDateChooser.getDate().getTime());

            PreparedStatement statement = conn.prepareStatement(SqlQueryConstant.GET_ALL_COMMISION_OF_EACH_OWNER);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            return bookingMapper.ResultSetToListOfCommissionOwner(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

