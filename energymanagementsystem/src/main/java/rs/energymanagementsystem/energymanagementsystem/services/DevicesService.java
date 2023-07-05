package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.format.annotation.DateTimeFormat;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;

import java.util.List;

public interface DevicesService {

    Devices saveDeviceViaForm(Devices devices);
    Devices saveDevice(Devices devices);

    List<Devices> getAllDevices();

    Devices getDeviceById(Integer id);

    Devices updateDeviceById(Integer device_id);
    Devices updateDevice(Devices devices, Integer device_id);

    void deleteDevice(Integer device_id);

    void deviceActiveFlag(Integer device_id);

    List<Devices> getActiveDevices();
}
