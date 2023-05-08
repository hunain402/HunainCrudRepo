package org.carrental.domain;

import lombok.*;

import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookingdetails {
    private Long id;
    private Double price;
    private String status;
    private Date  bookingdate;
    private Date  enddate;
    private Long  customerid;
    private Long  vehicleid;
    private String customername;
    private String vehiclename;
    private Double commision;
    private String owner_name;
    private Long owner_id;
    private Long owner_commision;
    private Integer noOfDays;
    private Double total_amount;




}
