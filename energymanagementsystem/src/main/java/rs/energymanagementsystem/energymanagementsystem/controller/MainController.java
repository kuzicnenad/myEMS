package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityLiveDataService;
import rs.energymanagementsystem.energymanagementsystem.services.GasLiveDataService;
import rs.energymanagementsystem.energymanagementsystem.services.WaterLiveDataService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ElectricityLiveDataService electricityLiveDataService;
    @Autowired
    private GasLiveDataService gasLiveDataService;
    @Autowired
    private WaterLiveDataService waterLiveDataService;

    @GetMapping(value={"","/"})
    public String showLogInScreen(){
        return "logInScreen";
    }

    @GetMapping(value={"/index"})
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/liveData")
    public String getLastData(Model model){
        List<ElectricityLiveData> lastElectricityData = electricityLiveDataService.getLastData();
        model.addAttribute("lastElectricityData", lastElectricityData);

        List<GasLiveData> lastGasData = gasLiveDataService.getLastData();
        model.addAttribute("lastGasData", lastGasData);

        List<WaterLiveData> lastWaterData = waterLiveDataService.getLastData();
        model.addAttribute("lastWaterData", lastWaterData);

        return "liveData";
    }
}
