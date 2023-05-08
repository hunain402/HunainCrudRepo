package org.carrental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Vehicle {
private Long id;
private String vehiclename;
private Long vehiclemodel;
private String vehiclebrand;
private String vehiclecolour;
private String owner_name;
private Long onwerid;
private String ownernumber;
private String status;
}
