package org.carrental.dao;

import org.carrental.domain.Bookingdetails;
import org.carrental.domain.Customer;
import org.carrental.domain.Owner;
import org.carrental.mapper.CustomerMapper;
import org.carrental.mapper.OwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.UPDATE_CUSTOMER_IS_DELETED_BY_ID;

//                                  THIS CLASS PERFORM CRUD OPERATIONS OF DATABASE TABLE(OWNER TABLE)
//                                  EXTENDS WITH BASE DAO CLASS FOR CONNECTION WITH DATA BASE AND IMPLEMENT WITH I CRUD
//
    public class OwnerDao extends BaseDao implements ICrud<Owner>{
    OwnerMapper ownerMapper = new OwnerMapper();

//*************************************  INSERT INTO OWNER  *************************************************************

    @Override
    public void insert(Owner obj) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_VEHICLE_OWNER);
            ps.setString(1, obj.getOwnername());
            ps.setString(2, obj.getOwnercnic());
            ps.setString(3, obj.getOwnernumber());
            ps.setString(4, obj.getOwneraddress());
            ps.setFloat(5, obj.getOwnercommision());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//*************************************  GET ALL FROM OWNER  *************************************************************

    @Override
            public List<Owner> getAll() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SqlQueryConstant.GET_ALL_FROM_OWNER);
            return ownerMapper.resultSetToList(rs);
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Owner> getAllOwnerExceptDelete() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_OWNER_EXCEPT_DELETE);
            return new OwnerMapper().resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //*************************************  GET OWNER BY ID  *************************************************************
    @Override
    public Owner GetById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_OWNER_BY_ID);
            ps.setInt(1, id.intValue());
            ResultSet rs = ps.executeQuery();
            return ownerMapper.resultSetToListObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //*************************************  UPDATE INTO OWNER  *************************************************************
    @Override
    public void Update(Owner obj, Long id) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_OWNER_BY_ID);
            ps.setString(1,obj.getOwnername());
            ps.setString(2, obj.getOwnercnic());
            ps.setString(3, obj.getOwnernumber());
            ps.setString(4, obj.getOwneraddress());
            ps.setFloat(5,obj.getOwnercommision());
            ps.setInt(6,obj.getId());
            ps.executeUpdate();
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //*************************************  DELETE OWNER BY ID  *************************************************************
    @Override
    public void DeleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.DELETE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDeleteOwnerById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_OWNER_IS_DELETED_BY_ID);
            ps.setLong(1, id.longValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Owner> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from vehicle_owner where owner_name like '%" + name + "%'");
            ResultSet rs = ps.executeQuery();
            return ownerMapper.resultSetToList(rs);
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bookingdetails> getOwners(Integer date, String ownerName) {
        try {
            PreparedStatement statement = conn.prepareStatement(SqlQueryConstant.GET_ALL_DATA_WITH_SELECTED_YEAR_AND_OWNER);
            statement.setString(1, date+"'-01-01'");
            statement.setString(2, date+"'-12-31'");
            statement.setString(3, ownerName);
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.yearlyRepOwner(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
