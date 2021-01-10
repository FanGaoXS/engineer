package com.blctek.authserver.service;

import com.blctek.carserver.pojo.Driver;
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
        String url = CAR_BASE_URL + "/vehicle/addVehicle/"
                + chipId + "/"
                + plateType + "/"
                + vehicleModel + "/"
                + plateNumber + "/"
                + driverName + "/"
                + driverPhone ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Boolean.class);

    }

    public Boolean vehicleDelete(Integer vehicleId,
                                 Integer carId,
                                 Integer driverId){
        String url = CAR_BASE_URL + "/vehicle/removeVehicle/"
                + vehicleId + "/"
                + carId + "/"
                + driverId ;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Boolean.class);
    }

    public Boolean vehicleUpdate(Vehicle vehicle, Driver driver){
        String url = CAR_BASE_URL + "/vehicle/modifyVehicle?"
                + "vehicleId={vehicleId}&" +
                "plateNumber={plateNumber}&" +
                "plateType={plateType}&" +
                "vehicleModel={vehicleModel}&" +
                "car={car}&" +
                "carId={carId}&" +
                "driver={driver}&" +
                "driverId={driverId}&" +
                "driverName={driverName}" ;
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("vehicleId",vehicle.getVehicleId());
        reqMap.put("plateNumber",vehicle.getPlateNumber());
        reqMap.put("plateType",vehicle.getPlateType());
        reqMap.put("vehicleModel",vehicle.getVehicleModel());
        reqMap.put("car",vehicle.getCar());
        reqMap.put("carId",vehicle.getCarId());
        reqMap.put("driver",vehicle.getDriver());
        reqMap.put("driverId",driver.getDriverId());
        reqMap.put("driverName",driver.getDriverName());
        return restTemplate.getForObject(url,Boolean.class,reqMap);
    }

    public List<Vehicle> vehicleList(){
        String url = CAR_BASE_URL + "/vehicle/allVehicle";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

}
