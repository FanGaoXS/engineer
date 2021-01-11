package com.blctek.authserver.service;

import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.pojo.Vehicle;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/08/17:14
 * @Description:    基于http请求的对car-server服务器的操作
 */
@Service
public class CarHttpService {

    //car服务器
    public static String CAR_BASE_URL = "http://localhost:8092";

    public Boolean vehicleInsert(String chipId,
                                 String plateType,
                                 String vehicleModel,
                                 String plateNumber,
                                 String driverName,
                                 String driverPhone){
        String url = CAR_BASE_URL + "/vehicle/addVehicle/" +
                chipId + "/" +
                plateType + "/" +
                vehicleModel + "/" +
                plateNumber + "/" +
                driverName + "/" +
                driverPhone ;
        return new RestTemplate().getForObject(url, Boolean.class);

    }

    public Boolean vehicleDelete(Integer vehicleId,
                                 Integer carId,
                                 Integer driverId){
        String url = CAR_BASE_URL + "/vehicle/removeVehicle/" +
                vehicleId + "/" +
                carId + "/" +
                driverId ;
        return new RestTemplate().getForObject(url, Boolean.class);
    }

    public Boolean vehicleUpdate(Vehicle vehicle, Driver driver){
        String url = CAR_BASE_URL + "/vehicle/modifyVehicle/" +
                vehicle.getVehicleId() + "/" +
                vehicle.getPlateNumber() + "/" +
                vehicle.getPlateType() + "/" +
                vehicle.getVehicleModel() + "/" +
                vehicle.getCarId() + "/" +
                driver.getDriverId() + "/" +
                driver.getDriverName() + "/" +
                driver.getDriverPhone() ;
        return new RestTemplate().getForObject(url ,Boolean.class);
    }

    public List<Vehicle> vehicleList(){
        String url = CAR_BASE_URL + "/vehicle/allVehicle";
        return new RestTemplate().getForObject(url, List.class);
    }

    public Boolean machineInsert(String chipId,
                                 String machineModel,
                                 String machineNumber,
                                 String engineNumber,
                                 String driverName,
                                 String driverPhone){
        String url = CAR_BASE_URL + "/machine/addMachine/" +
                chipId + "/" +
                machineModel + "/" +
                machineNumber + "/" +
                engineNumber + "/" +
                driverName + "/" +
                driverPhone ;
        return new RestTemplate().getForObject(url, Boolean.class);
    }

    public Boolean machineDelete(Integer machineId,
                                 Integer carId,
                                 Integer driverId){
        String url = CAR_BASE_URL + "/machine/removeMachine/" +
                machineId + "/" +
                carId + "/" +
                driverId ;
        return new RestTemplate().getForObject(url, Boolean.class);
    }

    public Boolean machineUpdate(Machine machine,Driver driver) {
        String url = CAR_BASE_URL + "/machine/modifyMachine/" +
                machine.getMachineId() + "/" +
                machine.getMachineNumber() + "/" +
                machine.getEngineNumber() + "/" +
                machine.getMachineModel() + "/" +
                driver.getDriverId() + "/" +
                driver.getDriverName() + "/" +
                driver.getDriverPhone() ;
        return new RestTemplate().getForObject(url, Boolean.class);
    }

    public List<Machine> machineList() {
        String url = CAR_BASE_URL + "/machine/allMachine" ;
        return new RestTemplate().getForObject(url, List.class);
    }

}
