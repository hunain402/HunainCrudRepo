package org.carrental.domain;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    private Integer id;
    private String ownername;
    private String ownercnic;
    private String ownernumber;
    private String owneraddress;
    private Float ownercommision;
}
