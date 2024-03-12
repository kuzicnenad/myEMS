package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.AlarmDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmDataServiceImpl implements AlarmDataService {

    private final AlarmDataRepository alarmDataRepository;

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

    public AlarmData getAlarmDataById(Integer alarmId){
//        Optional<AlarmData> alarmData = alarmDataRepository.findById(alarmId);
//        if(alarmData.isPresent()){
//            return alarmData.get();
//        }else {
//            throw new ResourceNotFoundException("Alarm data", "alarmId", alarmId);
//        }
        // lambda expression simplify the above expression
        return alarmDataRepository.findById(alarmId).orElseThrow(() ->
                new ResourceNotFoundException("Alarm data", "alarmId", alarmId));
    }

    @Override
    public AlarmData updateAlarmDate(AlarmData alarmData, Integer alarmId) {
        // check if alarmId exists in database
        AlarmData existingAlarmData = alarmDataRepository.findById(alarmId).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarmId", alarmId));
        existingAlarmData.setAlarmCode(alarmData.getAlarmCode());
        existingAlarmData.setAlarmDesc(alarmData.getAlarmDesc());
        existingAlarmData.setTimeStamp(alarmData.getTimeStamp());
        // save existing alarmData to DB
        alarmDataRepository.save(existingAlarmData);
        return existingAlarmData;
    }

    @Override
    public void deleteAlarmData(Integer alarmId) {
        // check if alarm exists in database
        alarmDataRepository.findById(alarmId).orElseThrow(() ->
                new ResourceNotFoundException("AlarmData", "alarmId", alarmId));
        alarmDataRepository.deleteById(alarmId);
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
    public void alarmAckFlag(Integer alarmId){
        // check if alarm is already acknowledged
        Boolean checkValue = alarmDataRepository.ackValueCheck(alarmId);

        if (!checkValue)
            alarmDataRepository.alarmAckFlag(alarmId);
        else
            System.out.println("Alarm was already acknowledged");
    }


}
