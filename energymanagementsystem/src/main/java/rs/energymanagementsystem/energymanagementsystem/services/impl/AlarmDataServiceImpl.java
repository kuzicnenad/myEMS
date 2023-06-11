package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.repositories.AlarmDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

@Service
public class AlarmDataServiceImpl implements AlarmDataService {

    private AlarmDataRepository alarmDataRepository;

    public AlarmDataServiceImpl(AlarmDataRepository alarmDataRepository) {
        this.alarmDataRepository = alarmDataRepository;
    }

    @Override
    public AlarmData saveAlarmData(AlarmData alarmData) {
        return alarmDataRepository.save(alarmData);
    }
}
