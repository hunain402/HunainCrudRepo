package org.carrental.domain;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

    private Long id;

    private String customername;

    private String customernumber;

    private String customercnic;

    private String customeraddress;

    private String customerrefnumber;

    private String status;


}
