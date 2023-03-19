package org.carrental.domain;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private Long id;
    private String  customerName;
    private String  customerNumber;
    private String  customerCnic;
    private String  customerAddress;
    private String  customerRefNumber;


}
