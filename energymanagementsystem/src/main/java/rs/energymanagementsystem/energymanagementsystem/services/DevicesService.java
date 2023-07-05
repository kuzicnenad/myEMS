package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.format.annotation.DateTimeFormat;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;

import java.util.List;

public interface DevicesService {

    /** ---------------------------------------------------------------------------------------
     * Get all devices and get alarm data record by ID.
     * Basic CRUD functionality, used for API testing
     --------------------------------------------------------------------------------------- **/
    Devices saveDeviceViaForm(Devices devices);
    Devices saveDevice(Devices devices);

    List<Devices> getAllDevices();

    Devices getDeviceById(Integer id);

    Devices updateDeviceById(Integer device_id);
    Devices updateDevice(Devices devices, Integer device_id);

    void deleteDevice(Integer device_id);

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    void deviceActiveFlag(Integer device_id);

    List<Devices> getActiveDevices();

    /** ---------------------------------------------------------------------------------------
     * Additional services for data visualisation on index page
     * Number of active devices
     * Number of inactive devices
     --------------------------------------------------------------------------------------- **/
    Integer getNumberOfActiveDevices();
    Integer getNumberOfInactiveDevices();

}
