package org.carrental;

import com.sun.deploy.security.ruleset.RuleSetParser;
import com.toedter.calendar.JDateChooser;
import javafx.util.Builder;
import org.carrental.UI.LoginUI;
import org.carrental.dao.*;
import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.Owner;
import org.carrental.domain.Vehicle;
import org.carrental.service.BookingService;
import org.carrental.service.ReportService;
import org.carrental.service.VehicleService;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Main {
        public static void main(String[] args) {


//                                            INSERT INTO CUSTOMER
//                                            VALUES INITIALIZE HERE

            CustomerDao customerDao = new CustomerDao();

//                         Customer newCus = Customer.builder()
//                        .customername("rashid")
//                                 .customernumber("03020303030")
//                                 .customercnic("440220202")
//                                 .customeraddress("streetfu")
//                                 .customerrefnumber("sgdgcdg")
//                                 .build();
//                         customerDao.insert(newCus);

//                                             GET ALL FROM CUSTOMER

//                   customerDao.getAll().forEach(System.out::println);

//vehicle
//                 VehicleDao vehicleDao= new VehicleDao();
//                 Vehicle newVeh = Vehicle.builder()
//                .vehiclename("cherry")
//                .vehiclemodel(2023L)
//                .vehiclebrand("japanese")
//                .vehiclecolour("white")
//                .onwerid(2L)
//                .build();
//                vehicleDao.insert(newVeh);

//                                            GET CUSTOMER BY ID

//                System.out.println (customerDao.GetById(2L));

//
//                                           UPDATE INTO CUSTOMER

//                Customer updatecustomer = customerDao.Update();

            // System.out.println(customerDao.GetById(2L));

//                                            DELETE CUSTOMER BY ID
//
//               customerDao.DeleteById(23L);
//               customerDao.getAll().forEach(System.out::println);
//
//
//                                             GET CUSTOMER BY NAME
//              System.out.println(customerDao.getByName("aleem"));
//                     OR
//             customerDao.getByName("aleem").forEach(System.out::println);


//                                             INSERT INTO BOOKING
//                                            VALUES INITIALIZE HERE
//
            BookingDao bookingDao = new BookingDao();
//            Booking insertintobooking = Booking.builder()
//                    .price(999.0)
//                    .status("active")
//                    .booking_date(new java.sql.Date(System.currentTimeMillis()))       //FOR CURRENT DAT
//                    .booking_date(java.sql.Date.valueOf("2022-06-15"))                //FOR HARD COATED DATE
//                    .customerid(1)                                                    //*\USE ONE DATE FORMAT
//                    .Vehicleid(2)                                                     // AT A TIME OTHERWISE
//                    .build();                                                    // YOU WILL FACE AMBIGUITY/*                             bookingDao.insert(insertintobooking);
//            bookingDao.insert(insertintobooking);

//                                            GET ALL FROM BOOKING
//
//
            // bookingDao.getAll().forEach(System.out::println);


//                                              GET BOOKING BY ID

//         System.out.println(bookingDao.GetById(2l));

//
//                                             UPDATE INTO BOOKING

//
//        Booking updatebooking = bookingDao.GetById(4l); //use less line
//        updatebooking.setPrice(77.0);
//        bookingDao.Update(updatebooking,4l);
//        System.out.println(bookingDao.GetById(4l));

//                                               SECOND UPDATE METHOD
//
//                 Booking updatedBooking = Booking.builder()
//                .id(3L) // replace with the id of the booking you want to update
//                .price(4500.0)
//                .build();
//                bookingDao.Update(updatedBooking,3l);
//                System.out.println(bookingDao.GetById(3l));


//                                               DELETE BOOKING BY ID

//                bookingDao.DeleteById(4l);
//                bookingDao.getAll().forEach(System.out::println);

//                System.out.println(bookingDao.getByPrice(50));

//                                                GET ALL BOOKING DETAILS (INNER JOIN )

//            bookingDao.getAllBookingDetails().forEach(System.out::println);


//                                              INSERT INTO OWNER
//                                            VALUES INITIALIZE HERE

            OwnerDao ownerDao = new OwnerDao();
//                 Owner insertIntoOwner = Owner.builder()
//                .ownername("faraz")
//                .ownercnic("442041234")
//                .ownernumber("03451234")
//                .owneraddress("steer mustaqim")
//                .ownercommision(5.0f)
//                .build();
//                ownerDao.insert(insertIntoOwner);


//                                                 GET ALL FROM OWNER

//            ownerDao.getAll().forEach(System.out::println);


//                                                  GET ALL FROM OWNER


//             System.out.println(ownerDao.GetById(2l));


//                                                  UPDATE INTO OWNER

//             Owner updateowner = Owner.builder()
//                     .id(2)
//                     .ownername("khalid")
//                     .ownercnic("4250123")
//                     .build();
//             ownerDao.Update(updateowner,2l);

//                                                  DELETE OWNER BY ID

//        ownerDao.DeleteById(6l);
//        ownerDao.getAll().forEach(System.out::println);

//                                                  GET OWNER BY NAME

            //         ownerDao.getByName("vicky").forEach(System.out::println);


//                                                INSERT INTO VEHICLE

            VehicleDao vehicleDao = new VehicleDao();
//                     Vehicle insertintovehicle = Vehicle.builder()
//                            .vehiclename("landcruiser")
//                            .vehiclemodel(2022l)
//                            .vehiclebrand("toyota")
//                            .vehiclecolour("WHT")
//                            .onwerid(2L)
//                            .build();
//                    vehicleDao.insert(insertintovehicle);

//                                                GET ALL FROM VEHICLE


//             vehicleDao.getAll().forEach(System.out::println);

//                                                 GET VEHICLE BY ID

//               System.out.println(vehicleDao.GetById(2l));

//
//                                             UPDATE INTO VEHICLE

//                 Vehicle updatevehicle = Vehicle.builder()
//                 .vehiclename("hybrid")
//                 .vehiclecolour("grey")
//                 .id(7l)
//                 .build();
//                 vehicleDao.Update(updatevehicle,7l);

//
//                                             DELETE VEHICLE BY ID

//                  vehicleDao.DeleteById(7l);
//            bookingDao.getAllCid().forEach(System.out::println);

            //           vehicleDao.getAllVehicleWithOwnerName().forEach(System.out::println);
//vehicleDao.getAllAvailableVehicleWithOwnerName().forEach(System.out::println);

            // bookingDao.getSumOfAllIndividualBooking().forEach(System.out::println);

            // vehicleDao.getAllVehicleAvailable().forEach(System.out::println);

            //   bookingDao.leastFrequentCar("2023-06-1", "2023-06-29").forEach(System.out::println);

            //   new ReportService().yearlyRepOwner(2023,"ali");

            // Arrays.stream(new BookingService().getVehicleIdAndNameForDropDown()).forEach(System.out::println);
            //Arrays.stream(new BookingService().getCustomerIdforDropDown()).forEach(System.out::println);
        }}

