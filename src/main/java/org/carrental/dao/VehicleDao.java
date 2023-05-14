package org.carrental.dao;
import org.carrental.domain.Bookingdetails;
import org.carrental.domain.Vehicle;
import org.carrental.mapper.VehicleMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//                                   THIS CLASS PERFORM CRUD OPERATIONS OF DATABASE TABLE(VEHICLE)
//                                   EXTENDS WITH BASE DAO CLASS FOR CONNECTION WITH DATA BASE AND IMPLEMENT WITH I CRUD

public class VehicleDao extends BaseDao implements ICrud<Vehicle> {
    private final VehicleMapper vehicleMapper = new VehicleMapper();

    //************************************************   INSERT INTO VEHICLE   ********************************************
    @Override
    public void insert(Vehicle obj) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.INSERT_INTO_VEHICLE);
            ps.setString(1, obj.getVehiclename());
            ps.setLong(2, obj.getVehiclemodel());
            ps.setString(3, obj.getVehiclebrand());
            ps.setString(4, obj.getVehiclecolour());
           ps.setLong(5, obj.getOnwerid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bookingdetails> yearRepVehicle(Integer date, String vehicleName) {
        try {
            PreparedStatement statement = conn.prepareStatement(SqlQueryConstant.YEARLY_REPO_VEHCILCE);
            statement.setString(2, date + "-01-01");
            statement.setString(3, date +  "-12-31");
            statement.setString(1, vehicleName);
            ResultSet resultSet = statement.executeQuery();
            return VehicleMapper.yearlyRepoVehicle(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //************************************************  GET ALL FROM VEHICLE   ********************************************

    @Override
    public List<Vehicle> getAll() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //************************************************   GET VEHICLE BY ID  ********************************************

    @Override
    public Vehicle GetById(Long id) {

        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_VEHICLE_BY_ID);
            ps.setLong(1, id.longValue());
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToListObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //************************************************   UPDATE INTO VEHICLE  ********************************************

    @Override
    public void Update(Vehicle obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHICLE_BY_ID);
            ps.setString(1, obj.getVehiclename());
            ps.setLong(2, obj.getVehiclemodel());
            ps.setString(3, obj.getVehiclebrand());
            ps.setString(4, obj.getVehiclecolour());
            ps.setLong(5, obj.getOnwerid());
            ps.setInt(6, id.intValue());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //************************************************   DELETE VEHICLE BY ID   ********************************************

    @Override
    public void DeleteById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.UPDATE_VEHILCE_DELETE_BY_ID);
            ps.setLong(1, id.longValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public List<Vehicle> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from vehicle where vehicle_name like '%" + name + "%'");
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> getAllVehicles() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlQueryConstant.GET_ALL_VEHICLE);
            return vehicleMapper.resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//                                                GET ALL AVAILABLE VEHICLE

    public List<Vehicle> getAvailableVehicles() {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from vehicle v inner join booking b on v.id = b.vehicle_id where v.status = \"active\"\n");
       return vehicleMapper.resultSetToVList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 //                                        GET ALL VEHICLE WITH OWNER NAME

 public  List<Vehicle>getAllVehicleWithOwnerName(){
     try {
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("select v.id,v.owner_id ,v.vehicle_name,v.vehicle_model,v.vehicle_brand," +
                 "v.vehicle_colour,vo.owner_name from vehicle v inner join vehicle_owner vo on vo.id = v.owner_id WHERE" +
                 " v.status <> 'deleted';");
        return vehicleMapper.resultSetToList(rs);
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
//                                  GET ALL VEHICLE WHICH AVAILABLE OR STATUS = NULL;

    public  List<Vehicle>getAllAvailableVehicleWithOwnerName(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select v.id,v.vehicle_name,v.vehicle_model,v.vehicle_brand,v.vehicle_colour,v.owner_id,vo.owner_name,vo.owner_number from vehicle v " +
                    "inner join vehicle_owner vo on vo.id = v.owner_id where v.status is active");
            return vehicleMapper.rsToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle>getAllVehicleAvailable()
    {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from vehicle where status = 'active'");
            return vehicleMapper.AVAiT(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Vehicle>getAllVehicleFiveYearAvailable()
    {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT v.id, v.vehicle_name\n" +
                    "FROM vehicle v\n" +
                    "INNER JOIN vehicle_owner vo ON vo.id = v.owner_id\n" +
                    "WHERE v.status <> 'deleted';\n");
            return vehicleMapper.annualTransform(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    public List<Vehicle>getAllVehicleFiveYears()
//    {
//        try {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("\"SELECT v.id, v.vehicle_name, vo.owner_name \" +\n" +
//                    "\"FROM vehicle v \" +\n" +
//                    "\"INNER JOIN vehicle_owner vo ON vo.id = v.owner_id \" +\n" +
//                    "\"WHERE v.status <> 'deleted'\";\n");
//            return vehicleMapper.AVAiT(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

   // }


    public void changeVehicleStatus(String id){

        try {
           Statement ps = conn.prepareStatement("UPDATE vehicle SET status = 'booked' WHERE id =" +id);
           ps.executeUpdate("UPDATE vehicle SET status = 'booked' WHERE id =" +id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void changeVehicleStatusAfterCompleteBooking(Integer id){

        try {
            Statement ps = conn.prepareStatement("UPDATE vehicle SET status = 'active' WHERE id =" +id);
            ps.executeUpdate("UPDATE vehicle SET status = 'active' WHERE id =" +id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
