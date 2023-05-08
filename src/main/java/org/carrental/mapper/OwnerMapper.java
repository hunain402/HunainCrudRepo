package org.carrental.mapper;

import org.carrental.domain.Bookingdetails;
import org.carrental.domain.Owner;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerMapper implements IMapper<Owner>{
    @Override
    public List<Owner> resultSetToList(ResultSet rs) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (rs.next()) {
            Owner owner = Owner.builder()
                    .id(rs.getInt("id"))
                    .ownername(rs.getString("owner_name"))
                    .ownercnic(rs.getString("owner_cnic"))
                    .ownernumber(rs.getString("owner_number"))
                    .owneraddress(rs.getString("owner_address"))
                    .ownercommision(rs.getFloat("owner_commision"))
                    .build();
            ownerList.add(owner);

        }
        return ownerList;
    }

    @Override
    public Owner resultSetToListObject(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Owner owner = Owner.builder()
                    .id(rs.getInt("id"))
                    .ownername(rs.getString("owner_name"))
                    .ownercnic(rs.getString("owner_cnic"))
                    .ownernumber(rs.getString("owner_number"))
                    .owneraddress(rs.getString("owner_address"))
                    .ownercommision(rs.getFloat("owner_commision"))
                    .build();
            return owner;
        }
        return null;
    }
    public List<Bookingdetails> yearlyRepOwner(ResultSet resultSet) throws SQLException {
        List<Bookingdetails> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Bookingdetails owner = Bookingdetails.builder()
                    .owner_name(resultSet.getString("owner_name"))
                    .owner_commision(resultSet.getLong("owner_commision"))
                    .vehiclename(resultSet.getString("vehicle_name"))
                    .bookingdate(resultSet.getDate("booking_date"))
                    .enddate(Date.valueOf(resultSet.getString("end_date")))
                    .commision(Double.valueOf(resultSet.getInt("total_commision")))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }


    }

