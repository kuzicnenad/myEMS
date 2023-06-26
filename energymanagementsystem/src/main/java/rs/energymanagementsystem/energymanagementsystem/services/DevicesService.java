package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.Devices;

import java.util.List;

public interface DevicesService {

    Devices saveDevice(Devices devices);

    List<Devices> getAllDevices();

    Devices getDeviceById(Integer id);

    Devices updateDevice(Devices devices, Integer device_id);

    void deleteDevice(Integer device_id);
}