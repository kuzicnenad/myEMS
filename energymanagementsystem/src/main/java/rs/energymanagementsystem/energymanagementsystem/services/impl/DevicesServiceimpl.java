package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.DevicesRepository;
import rs.energymanagementsystem.energymanagementsystem.services.DevicesService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevicesServiceimpl implements DevicesService {

    private final DevicesRepository devicesRepository;

    /** Used for application database update via html form **/
    @Override
    public Devices saveDeviceViaForm(Devices devices) {
        return devicesRepository.save(devices);
    }

    /** Used for database update via API **/
    @Override
    public Devices saveDevice(Devices devices) {
        return devicesRepository.save(devices);
    }

    @Override
    public List<Devices> getAllDevices() {
        return  devicesRepository.findAll();
    }

    public Devices getDeviceById(Integer deviceId){
        return devicesRepository.findById(deviceId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "deviceId", deviceId));
    }

    /** Used for database update via API **/
    @Override
    public Devices updateDevice(Devices devices, Integer deviceId) {
        // check if deviceId exists in database
        Devices existingDevice = devicesRepository.findById(deviceId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "deviceId", deviceId));
        existingDevice.setDeviceName(devices.getDeviceName());
        existingDevice.setDescription(devices.getDescription());
        existingDevice.setProductionDate(devices.getProductionDate());
        existingDevice.setMadeIn(devices.getMadeIn());
        existingDevice.setLastUpdate(devices.getLastUpdate());
        // save existing device to DB
        devicesRepository.save(devices);
        return existingDevice;
    }
    /** Used for application database update via html form **/
    @Override
    public Devices updateDeviceById(Integer deviceId) {
        Optional< Devices > optional = devicesRepository.findById(deviceId);
        Devices devices = null;
        if (optional.isPresent()) {
            devices = optional.get();
        } else {
            return devicesRepository.findById(deviceId).orElseThrow(() ->
                    new ResourceNotFoundException("Device", "deviceId", deviceId));
        }
        return devices;
    }

    @Override
    public void deleteDevice(Integer deviceId) {
        // check if device exists in database
        devicesRepository.findById(deviceId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "deviceId", deviceId));
        devicesRepository.deleteById(deviceId);
    }

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    @Override
    public void deviceActiveFlag(Integer deviceId) {
        // check if device exists in database
        devicesRepository.findById(deviceId).orElseThrow(() ->
                new ResourceNotFoundException("Device", "deviceId", deviceId));
        devicesRepository.deviceActiveFlag(deviceId);
    }

    @Override
    public List<Devices> getActiveDevices() {
        return devicesRepository.getActiveDevices();
    }


    /** ---------------------------------------------------------------------------------------
     * Additional services for data visualisation on index page
     * Number of active devices
     * Number of inactive devices
     --------------------------------------------------------------------------------------- **/
    @Override
    public Integer getNumberOfActiveDevices() {
        return devicesRepository.numberOfActiveDevices();
    }

    @Override
    public Integer getNumberOfInactiveDevices() {
        return devicesRepository.numberOfInactiveDevices();
    }

}
