package org.carrental.domain;

import lombok.*;

import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private Long id;
    private Double price;
    private String status;
    private Date booking_date ;
    private Date end_date ;
    private Long  customerid;
    private Long  Vehicleid;


}
