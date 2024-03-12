package rs.energymanagementsystem.energymanagementsystem.services;

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

    Devices updateDeviceById(Integer deviceId);
    Devices updateDevice(Devices devices, Integer deviceId);

    void deleteDevice(Integer deviceId);

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    void deviceActiveFlag(Integer deviceId);

    List<Devices> getActiveDevices();

    /** ---------------------------------------------------------------------------------------
     * Additional services for data visualisation on index page
     * Number of active devices
     * Number of inactive devices
     --------------------------------------------------------------------------------------- **/
    Integer getNumberOfActiveDevices();
    Integer getNumberOfInactiveDevices();

}
