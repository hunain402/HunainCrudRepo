package org.carrental.service;

import org.carrental.dao.VehicleDao;
import org.carrental.domain.Owner;
import org.carrental.domain.Vehicle;

import java.util.List;

public class VehicleService {
    private final VehicleDao vehicleDao = new VehicleDao();

  //add
    public void save(String name, String model, String brand, String colour, String ownerId) {
        Vehicle vehicle = Vehicle.builder()
                .vehiclename(name)
                .vehiclemodel(Long.valueOf(model))
                .vehiclebrand(brand)
                .vehiclecolour(colour)
                .onwerid(Long.valueOf(ownerId))
                .build();
        vehicleDao.insert(vehicle);
    }

    //edit
    public void update(String id, String name, long model, String brand, String colour, long ownerId) {
        Vehicle vehicle = Vehicle.builder()
                .vehiclename(name)
                .vehiclemodel(model)
                .vehiclebrand(brand)
                .vehiclecolour(colour)
                .onwerid(ownerId)
                .build();
        vehicleDao.Update(vehicle, Long.valueOf(id));
    }

    public void delete(Long id) {
        vehicleDao.DeleteById(Long.valueOf(Long.valueOf(id)));
    }

    public void softDelete(Long id) {
        vehicleDao.DeleteById(Long.valueOf(Long.valueOf(id)));
    }

    public String[][] getAllVehicleForJtable() {
        List<Vehicle> vehicleList = vehicleDao.getAllVehicleWithOwnerName();
        return TransformToTable(vehicleList, 6);
    }

    public String[][] getAllAvailableVehicleForJtable() {
        List<Vehicle> vehicleList = vehicleDao.getAllAvailableVehicleWithOwnerName();
        return TransformAvailableVehicleToTableToTable(vehicleList, 8);
    }


    public String[][] getAllAvailableV() {
        List<Vehicle> vehicleList = vehicleDao.getAllVehicleAvailable();
        return TT(vehicleList, 2);
    }

//public String[][]getCommissionOfAllOwner(){
//  List<Owner>ownerList= OwnerDao.getAllCommisionWithOwnerName();
//  return commisionToList(ownerList,3);
//}
//


//    public String[][] getByName(String name) {
//        List<Vehicle> vehicleList = vehicleDao.getByName(name);
//        return TransformToTable(vehicleList, 6);
//    }

    private static String[][] TransformToTable(List<Vehicle> vehicleList, Integer columnSize) {
        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehiclename();
            data[i][2] = String.valueOf(vehicleList.get(i).getVehiclemodel());
            data[i][3] = String.valueOf(vehicleList.get(i).getVehiclebrand());
            data[i][4] = String.valueOf(vehicleList.get(i).getVehiclecolour());
            data[i][5] = String.valueOf(vehicleList.get(i).getOwner_name());
//            data[i][6] = String.valueOf(vehicleList.get(i).getOnwerid());
//            data[i][7] = String.valueOf(vehicleList.get(i).getOwnernumber());
        }
        return data;
    }

 private static String[][] TransformAvailableVehicleToTableToTable(List<Vehicle> vehicleList, Integer columnSize) {
            String[][] data = new String[vehicleList.size()][columnSize];
            for (int i = 0; i < vehicleList.size(); i++) {
                data[i][0] = String.valueOf(vehicleList.get(i).getId());
                data[i][1] = vehicleList.get(i).getVehiclename();
                data[i][2] = String.valueOf(vehicleList.get(i).getVehiclemodel());
                data[i][3] = String.valueOf(vehicleList.get(i).getVehiclebrand());
                data[i][4] = String.valueOf(vehicleList.get(i).getVehiclecolour());
                data[i][5] = String.valueOf(vehicleList.get(i).getOwner_name());
                data[i][6] = String.valueOf(vehicleList.get(i).getOnwerid());
                data[i][7] = String.valueOf(vehicleList.get(i).getOwnernumber());
            }
            return data;
        }

    private static String[][] TT(List<Vehicle> vehicleList, Integer columnSize) {
        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehiclename();
           // data[i][2] = String.valueOf(vehicleList.get(i).getVehiclemodel());
           // data[i][3] = String.valueOf(vehicleList.get(i).getVehiclebrand());
            //data[i][4] = String.valueOf(vehicleList.get(i).getVehiclecolour());
            //data[i][5] = String.valueOf(vehicleList.get(i).getOwner_name());
            //data[i][6] = String.valueOf(vehicleList.get(i).getOnwerid());
            //data[i][7] = String.valueOf(vehicleList.get(i).getOwnernumber());
        }
        return data;
    }


    private static  String[][]commisionToList (List<Owner> ownerList, Integer columnSize) {
        String[][] data = new String[ownerList.size()][columnSize];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i][0] = String.valueOf(ownerList.get(i).getId());
            data[i][1] = ownerList.get(i).getOwnername();
            data[i][2] = String.valueOf(ownerList.get(i).getOwnercommision());
        }
        return data;
    }

//        public List<Vehicle>  getAvailableVehicle(){
//        List<Vehicle> vehlist = vehicleDao.getAvailableVehicles();
//        return vehlist;
//    }
}


