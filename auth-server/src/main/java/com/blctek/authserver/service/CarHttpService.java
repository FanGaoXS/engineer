package com.blctek.authserver.service;

import com.blctek.carserver.pojo.Driver;
import com.blctek.carserver.pojo.Machine;
import com.blctek.carserver.pojo.Model;
import com.blctek.carserver.pojo.Vehicle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

    //image服务器
    public static String IMG_BASE_URL = "http://localhost:8093";

    public String imageUpload(MultipartFile file){
        String url = IMG_BASE_URL + "/img/imgInsert";
        System.out.println(file.getOriginalFilename());

        //post请求的data
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("file",file);

        //http请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "multipart/form-data");
        httpHeaders.set("Accept","text/plain");

        //将请求头和请求头整合成请求实体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, httpHeaders);

        return new RestTemplate().postForObject(url, requestEntity, String.class);
    }

    public Boolean vehicleInsert(String chipId,
                                 String plateType,
                                 String vehicleModel,
                                 String plateNumber,
                                 String driverName,
                                 String driverPhone,
                                 String imagePath){
        String url = CAR_BASE_URL + "/vehicle/addVehicle/" +
                chipId + "/" +
                plateType + "/" +
                vehicleModel + "/" +
                plateNumber + "/" +
                driverName + "/" +
                driverPhone + "/" +
                imagePath;
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


    public Boolean machineInsert(String chipId,
                                 String machineModel,
                                 String machineNumber,
                                 String engineNumber,
                                 String driverName,
                                 String driverPhone,
                                 String imagePath){
        String url = CAR_BASE_URL + "/machine/addMachine/" +
                chipId + "/" +
                machineModel + "/" +
                machineNumber + "/" +
                engineNumber + "/" +
                driverName + "/" +
                driverPhone + "/" +
                imagePath;
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

    public List<Vehicle> vehicleList(){
        String url = CAR_BASE_URL + "/vehicle/allVehicle";
        return new RestTemplate().getForObject(url, List.class);
    }

    public List<Model> modelList(){
        String url = CAR_BASE_URL + "/model/modelList";
        return new RestTemplate().getForObject(url, List.class);
    }

    public List<Model> modelListByBelong(String modelBelong){
        String url = CAR_BASE_URL + "/model/modelList/" + modelBelong;
        return new RestTemplate().getForObject(url, List.class);
    }

    public Boolean insertModel(Model model){
        String url = CAR_BASE_URL + "/model/insertModel?" +
                "modelName={modelName}&" +
                "modelDescription={modelDescription}&" +
                "modelExampleImage={modelExampleImage}&" +
                "modelBelong={modelBelong}";
        HashMap<String , Object> reqMap = new HashMap<>();
        reqMap.put("modelName",model.getModelName());
        reqMap.put("modelDescription",model.getModelDescription());
        reqMap.put("modelExampleImage",model.getModelExampleImage());
        reqMap.put("modelBelong",model.getModelBelong());
        return new RestTemplate().getForObject(url ,Boolean.class,reqMap);
    }

    public Boolean deleteModel(Integer id){
        String url = CAR_BASE_URL + "/model/deleteModel/" + id ;
        return new RestTemplate().getForObject(url,Boolean.class);
    }

    public Boolean updateModel(Model model){
        String url = CAR_BASE_URL + "/model/updateModel?" +
                "id={id}&" +
                "modelName={modelName}&" +
                "modelDescription={modelDescription}&" +
                "modelExampleImage={modelExampleImage}&" +
                "modelBelong={modelBelong}";
        HashMap<String , Object> reqMap = new HashMap<>();
        reqMap.put("id",model.getId());
        reqMap.put("modelName",model.getModelName());
        reqMap.put("modelDescription",model.getModelDescription());
        reqMap.put("modelExampleImage",model.getModelExampleImage());
        reqMap.put("modelBelong",model.getModelBelong());
        return new RestTemplate().getForObject(url ,Boolean.class,reqMap);
    }

}
