package org.carrental.dao;
import org.carrental.domain.Customer;
import org.carrental.mapper.CustomerMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.carrental.dao.SqlQueryConstant.DELETE_CUSTOMER_BY_ID;
import static org.carrental.dao.SqlQueryConstant.UPDATE_CUSTOMER_IS_DELETED_BY_ID;

/**THIS CLASS PERFORM CRUD OPERATIONS OF DATABASE TABLE
 EXTENDS WITH BASE DAO CLASS FOR CONNECTION WITH DATABASE AND IMPLEMENT WITH CRUD interface */


public class CustomerDao extends BaseDao implements ICrud<Customer> {

    /**    INSERT INTO CUSTOMER */
    @Override
    public void   insert   (Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_CUSTOMER);

            ps.setString(1,obj.getCustomername());

            ps.setString(2,obj.getCustomernumber());

            ps.setString(3,obj.getCustomercnic());

            ps.setString(4,obj.getCustomeraddress());

            ps.setString(5,obj.getCustomerrefnumber());

            ps.executeUpdate();
             }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**      GET ALL CUSTOMER */
    @Override
    public List<Customer> getAll() {
        try {
           Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CUSTOMER);
            return new CustomerMapper().resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getAllExceptDelete() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_CUSTOMER_EXCEPT_DELETE);
            return new CustomerMapper().resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** GET CUSTOMER BY ID */
    @Override
    public Customer GetById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_CUSTOMER_BY_ID);
            ps.setLong(1,id.longValue());
            ResultSet rs = ps.executeQuery();
            return new CustomerMapper().resultSetToListObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
       }

    }


    /** UPDATE INTO CUSTOMER */
                                      @Override
    public void Update(Customer obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_CUSTOMER_BY_ID);
            ps.setString(1,obj.getCustomername());
            ps.setString(2, obj.getCustomernumber());
            ps.setString(3,obj.getCustomercnic());
            ps.setString(4,obj.getCustomeraddress());
            ps.setString(5, obj.getCustomerrefnumber());
            ps.setLong(6,id.longValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
/**      CUSTOMER DELETE BY ID */
    @Override
    public void DeleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_CUSTOMER_BY_ID);
            ps.setLong(1,id.longValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateDeleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_CUSTOMER_IS_DELETED_BY_ID);
            ps.setLong(1, id.longValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**   GET CUSTOMER BY NAME
          ADDITIONAL METHOD*/
    public List<Customer> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from customer where customer_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return new CustomerMapper().resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    }

