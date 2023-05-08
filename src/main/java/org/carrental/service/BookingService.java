package org.carrental.service;

import com.toedter.calendar.JDateChooser;
import org.carrental.dao.BookingDao;
import org.carrental.dao.CustomerDao;
import org.carrental.dao.VehicleDao;
import org.carrental.domain.*;

import java.sql.Date;
import java.util.List;

public class BookingService {


    BookingDao bookingDao = new BookingDao();

    public void add(String customerId,String vehicleId, java.util.Date bookingDate, String price) {
        Booking booking = Booking.builder()
                .customerid(Long.valueOf(Integer.valueOf(customerId)))
                .Vehicleid(Long.valueOf(Integer.valueOf(vehicleId)))
                .booking_date(new java.sql.Date(bookingDate.getTime()))
                .price(Double.valueOf(price))

                .build();

        bookingDao.insert(booking);
       new VehicleDao().changeVehicleStatus(vehicleId);
    }


    public void save(String id, String price, String status, String bookingDate, String customerId, String vehicleId) {
        Booking booking = Booking.builder()
                .price(Double.valueOf(price))
                .status(status)
                .booking_date(Date.valueOf(bookingDate))
                .customerid(Long.valueOf(Integer.valueOf(customerId)))
                .Vehicleid(Long.valueOf(Integer.valueOf(vehicleId)))
                .build();
        bookingDao.Update(booking, Long.valueOf(Integer.valueOf(id)));

    }

    public void delete(String id) {
        bookingDao.DeleteById(Long.valueOf(Integer.valueOf(id)));
    }

    public void softDelete(String id) {
        bookingDao.DeleteById(Long.valueOf(Long.valueOf(id)));
    }

    //
    public String[][] getAllBookingForJTable() {
        List<Bookingdetails> bookingdetails = bookingDao.getAllBookingDetails();
        return transformToJTable(bookingdetails, 8);
    }
    public String [][] getALLOwnerNameWithcommision(JDateChooser startDateChooser,JDateChooser endDateChooser){
      List<Bookingdetails>bookingdetails = bookingDao.getEveryOwnerCommision(startDateChooser,endDateChooser);
      return everyOwnerCommision(bookingdetails,3);
    }


    private String[][] everyOwnerCommision(List<Bookingdetails> bookingdetails, int columnSize) {

        String[][] data = new String[bookingdetails.size()][columnSize];

        for (int i = 0; i < bookingdetails.size(); i++) {

           // data[i][0] = String.valueOf(bookingdetails.get(i).getOwner_id());
            data[i][0] = bookingdetails.get(i).getOwner_name();
            data[i][1] = String.valueOf(bookingdetails.get(i).getCommision());
            data[i][2] = String.valueOf(bookingdetails.get(i).getOwner_commision());



        }
        return data;
    }


    public String[][] analyticsReports(JDateChooser startDateChooser, JDateChooser endDateChooser) {
        List<Bookingdetails> mostFrequentCar = bookingDao.mostFrequentCar(startDateChooser, endDateChooser);
        List<Bookingdetails> leastFrequentCar = bookingDao.leastFrequentCar(startDateChooser, endDateChooser);
        List<Bookingdetails> highestRevenueCarOfTheMonth = bookingDao.highestRevenueCarOfTheMonth(startDateChooser, endDateChooser);
        List<Bookingdetails> maxCommisionOfTheMonth = bookingDao.maxCommisionOfTheMonth(startDateChooser, endDateChooser);
        return transformAmountToJTable(mostFrequentCar,leastFrequentCar, highestRevenueCarOfTheMonth,maxCommisionOfTheMonth,4);
    }

    //A
    public String[][] getAll30DaysBookingForJTable(JDateChooser startDateChooser, JDateChooser endDateChooser) {
        List<Bookingdetails> bookingdetails = bookingDao.getAll30DaysBookingDetails(startDateChooser, endDateChooser);
        return transformToJTableMonthly(bookingdetails, 9);
    }

    //
    private String[][] transformToJTable(List<Bookingdetails> bookingdetails, int columnSize) {

        String[][] data = new String[bookingdetails.size()][columnSize];

        for (int i = 0; i < bookingdetails.size(); i++) {

            data[i][0] = String.valueOf(bookingdetails.get(i).getId());
            data[i][1] = String.valueOf(bookingdetails.get(i).getPrice());
            data[i][2] = bookingdetails.get(i).getStatus();
            data[i][3] = String.valueOf(bookingdetails.get(i).getBookingdate());
            data[i][6] = String.valueOf(bookingdetails.get(i).getEnddate());
            data[i][5] = bookingdetails.get(i).getVehiclename();
            data[i][4] = bookingdetails.get(i).getCustomername();
            data[i][7] = String.valueOf(bookingdetails.get(i).getPrice());

        }
        return data;
    }

    private String[][] transformToJTableMonthly(List<Bookingdetails> bookingdetails, int columnSize) {

        String[][] data = new String[bookingdetails.size()][columnSize];

        for (int i = 0; i < bookingdetails.size(); i++) {

            data[i][0] = String.valueOf(bookingdetails.get(i).getId());
            data[i][1] = String.valueOf(bookingdetails.get(i).getPrice());
            data[i][2] = bookingdetails.get(i).getStatus();
            data[i][3] = String.valueOf(bookingdetails.get(i).getBookingdate());
            data[i][6] = String.valueOf(bookingdetails.get(i).getEnddate());
            data[i][5] = bookingdetails.get(i).getVehiclename();
            data[i][4] = bookingdetails.get(i).getCustomername();
            data[i][7] = String.valueOf(bookingdetails.get(i).getTotal_amount());
            data[i][8] = String.valueOf(bookingdetails.get(i).getNoOfDays());

        }
        return data;
    }


    //
    private String[][] transformAmountToJTable(List<Bookingdetails> mostfrequent, List<Bookingdetails> leastfrequent,
                                               List<Bookingdetails>highestRevenueCarOfTheMonth,
                                               List<Bookingdetails>maxCommisionOfTheMonth,int columnSize) {
        String[][] data = new String[mostfrequent.size()][columnSize];



            data[0][0] = String.valueOf(mostfrequent.get(0).getVehicleid());
            data[0][0] +=" " + mostfrequent.get(0).getVehiclename();



            data[0][1] = String.valueOf(leastfrequent.get(0).getVehicleid());
            data[0][1] += " " + leastfrequent.get(0).getVehiclename();


            data[0][2] = String.valueOf(highestRevenueCarOfTheMonth.get(0).getVehicleid());
            data[0][2] += " " + highestRevenueCarOfTheMonth.get(0).getVehiclename();



            data[0][3] = String.valueOf(maxCommisionOfTheMonth.get(0).getOwner_id());
            data[0][3] += " " + maxCommisionOfTheMonth.get(0).getOwner_name();


        return data;
    }
    /////////////////////////
    public String[] getCustomerIdforDropDown() {
        List<Customer> customerList = new CustomerDao().getAll();
        String[] data = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = String.valueOf((customerList.get(i).getId()));
            data[i] += "  " + customerList.get(i).getCustomername();
        }
        return data;
    }

    public String[] getVehicleIdAndNameForDropDown() {
        List<Vehicle> vehicleList = new VehicleDao().getAllVehicleAvailable();
        String[] data = new String[vehicleList.size()];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = String.valueOf(vehicleList.get(i).getId());
            data[i] += "  " + vehicleList.get(i).getVehiclename();

        }
        return data;
    }

    public String[] getVehicleIdAndNameForFiveYearDropDown() {
        List<Vehicle> vehicleList = new VehicleDao().getAllVehicleFiveYearAvailable();
        String[] data = new String[vehicleList.size()];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = String.valueOf(vehicleList.get(i).getId());
            data[i] += "  " + vehicleList.get(i).getVehiclename();

        }
        return data;
    }


    //**********************************************************************
    public String[][] searchByPrice(double price) {
        List<Booking> bookingList = bookingDao.getByPrice(price);
        return convertListTo2DArray(bookingList, 6);
    }

    private String[][] convertListTo2DArray(List<Booking> bookingList, Integer columnSize) {
        String[][] data = new String[bookingList.size()][columnSize];
        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getCustomerid());
            data[i][1] = String.valueOf(bookingList.get(i).getVehicleid());
            data[i][2] = String.valueOf(bookingList.get(i).getBooking_date());
            data[i][3] = String.valueOf(bookingList.get(i).getPrice());
        }
        return data;
    }

    public void completeBookingService(String id, JDateChooser endDateChooser) {
        bookingDao.completeBooking(id, endDateChooser);
        new VehicleDao().changeVehicleStatusAfterCompleteBooking(id);
    }


    public Double totalProfit(JDateChooser startDateChooser, JDateChooser endDateChooser) {
        List<Bookingdetails> bookingList = bookingDao.getAll30DaysBookingDetails(startDateChooser, endDateChooser);
        Double profit = 0.0;
        for (int i = 0; i < bookingList.size(); i++) {
            profit += bookingList.get(i).getTotal_amount();

        }
        return profit;
    }


    public Double everyOwnerCommision(JDateChooser startDateChooser, JDateChooser endDateChooser) {
        List<Bookingdetails> bookingList = bookingDao.getEveryOwnerCommision(startDateChooser, endDateChooser);
        Double commision = 0.0;
        for (int i = 0; i < bookingList.size(); i++) {
            commision += bookingList.get(i).getCommision();

        }
        return commision;
    }




//    public List<Owner>getAllCommisionWithOwnerName(JDateChooser startDateChooser,JDateChooser endDateChooser){
//
//    }

    //                                        TRANSFORM DATA INTO 2D OR TABLE FORM


//    private getMonthlyCommisionForJTable(JDateChooser startDateChooser, JDateChooser endDateChooser) {
//    }re
}
