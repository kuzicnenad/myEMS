package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.DevicesRepository;
import rs.energymanagementsystem.energymanagementsystem.services.DevicesService;

import java.util.List;
import java.util.Optional;

@Service
public class DevicesServiceimpl implements DevicesService {

    private DevicesRepository devicesRepository;

    public DevicesServiceimpl(DevicesRepository devicesRepository) {
        this.devicesRepository = devicesRepository;
    }

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

    public Devices getDeviceById(Integer device_id){
        return devicesRepository.findById(device_id).orElseThrow(() ->
                new ResourceNotFoundException("Device", "device_id", device_id));
    }

    /** Used for database update via API **/
    @Override
    public Devices updateDevice(Devices devices, Integer device_id) {
        // check if device_id exists in database
        Devices existingDevice = devicesRepository.findById(device_id).orElseThrow(() ->
                new ResourceNotFoundException("Device", "device_id", device_id));
        existingDevice.setDevice_name(devices.getDevice_name());
        existingDevice.setDescription(devices.getDescription());
        existingDevice.setProduction_date(devices.getProduction_date());
        existingDevice.setMade_in(devices.getMade_in());
        existingDevice.setLast_update(devices.getLast_update());
        // save existing device to DB
        devicesRepository.save(devices);
        return existingDevice;
    }
    /** Used for application database update via html form **/
    @Override
    public Devices updateDeviceById(Integer device_id) {
        Optional< Devices > optional = devicesRepository.findById(device_id);
        Devices devices = null;
        if (optional.isPresent()) {
            devices = optional.get();
        } else {
            return devicesRepository.findById(device_id).orElseThrow(() ->
                    new ResourceNotFoundException("Device", "device_id", device_id));
        }
        return devices;
    }

    @Override
    public void deleteDevice(Integer device_id) {
        // check if device exists in database
        devicesRepository.findById(device_id).orElseThrow(() ->
                new ResourceNotFoundException("Device", "device_id", device_id));
        devicesRepository.deleteById(device_id);
    }

    @Override
    public void deviceActiveFlag(Integer device_id) {
        // check if device exists in database
        devicesRepository.findById(device_id).orElseThrow(() ->
                new ResourceNotFoundException("Device", "device_id", device_id));
        devicesRepository.deviceActiveFlag(device_id);
    }

    @Override
    public List<Devices> getActiveDevices() {
        return devicesRepository.getActiveDevices();
    }
}
