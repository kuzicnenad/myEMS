package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.AlarmDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

import java.util.List;

@Service
public class AlarmDataServiceImpl implements AlarmDataService {

    private AlarmDataRepository alarmDataRepository;

    public AlarmDataServiceImpl(AlarmDataRepository alarmDataRepository) {
        this.alarmDataRepository = alarmDataRepository;
    }

    /** ---------------------------------------------------------------------------------------
     * Get all alarm data records and get alarm data record by ID.
     * Basic CRUD functionality, used for API testing
     --------------------------------------------------------------------------------------- **/
    @Override
    public AlarmData saveAlarmData(AlarmData alarmData) {
        return alarmDataRepository.save(alarmData);
    }

    @Override
    public List<AlarmData> getAllAlarmData() {
        return  alarmDataRepository.findAll();
    }

    public AlarmData getAlarmDataById(Integer alarm_id){
//        Optional<AlarmData> alarmData = alarmDataRepository.findById(alarm_id);
//        if(alarmData.isPresent()){
//            return alarmData.get();
//        }else {
//            throw new ResourceNotFoundException("Alarm data", "alarm_id", alarm_id);
//        }
        // lambda expression simplify the above expression
        return alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("Alarm data", "alarm_id", alarm_id));
    }

    @Override
    public AlarmData updateAlarmDate(AlarmData alarmData, Integer alarm_id) {
        // check if alarm_id exists in database
        AlarmData existingAlarmData = alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarm_id", alarm_id));
        existingAlarmData.setAlarm_code(alarmData.getAlarm_code());
        existingAlarmData.setAlarm_desc(alarmData.getAlarm_desc());
        existingAlarmData.setTime_stamp(alarmData.getTime_stamp());
        // save existing alarmData to DB
        alarmDataRepository.save(existingAlarmData);
        return existingAlarmData;
    }

    @Override
    public void deleteAlarmData(Integer alarm_id) {
        // check if alarm exists in database
        alarmDataRepository.findById(alarm_id).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarm_id", alarm_id));
        alarmDataRepository.deleteById(alarm_id);
    }

    /** ---------------------------------------------------------------------------------------
     * Get latest (last 10) records for index page visualisation
     * Acknowledge alarm flag, 0 -> Not Acknowledged, 1 -> Acknowledged
     --------------------------------------------------------------------------------------- **/
    @Override
    public List<AlarmData> getLatestDate() {
        return alarmDataRepository.getLatestData();
    }

    @Override
    public void alarmAckFlag(Integer alarm_id){
        // check if alarm is already acknowledged
        Boolean checkValue = alarmDataRepository.ackValueCheck(alarm_id);

        if (!checkValue)
            alarmDataRepository.alarmAckFlag(alarm_id);
        else
            System.out.println("Alarm was already acknowledged");
    }


}
