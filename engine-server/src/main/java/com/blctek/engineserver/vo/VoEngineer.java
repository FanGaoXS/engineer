package com.blctek.engineserver.vo;

import com.blctek.engineserver.pojo.Engineer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/10/14:33
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoEngineer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String type;

    private String deviceId;

    private Date inputTime;

    private String vehicleNumber;

    private String plateType;

    private String machineNumber;

    private String engineNumber;

    private VoDriver driver;

    private VoModel model;

    public VoEngineer(Engineer engineer) {
        this.id = engineer.getId();
        this.uuid = engineer.getUuid();
        this.type = engineer.getType();
        this.deviceId = engineer.getDeviceId();
        this.inputTime = engineer.getInputTime();
        this.vehicleNumber = engineer.getVehicleNumber();
        this.plateType = engineer.getPlateType();
        this.machineNumber = engineer.getMachineNumber();
        this.engineNumber = engineer.getEngineNumber();
        this.driver = new VoDriver(engineer.getDriver());
        this.model = new VoModel(engineer.getModel());
    }
}
