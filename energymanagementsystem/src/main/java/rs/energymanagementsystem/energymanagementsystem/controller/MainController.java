package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.energymanagementsystem.energymanagementsystem.entities.*;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityHistoryDataService;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityLiveDataService;
import rs.energymanagementsystem.energymanagementsystem.services.GasLiveDataService;
import rs.energymanagementsystem.energymanagementsystem.services.WaterLiveDataService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ElectricityLiveDataService electricityLiveDataService;

    @Autowired
    private ElectricityHistoryDataService electricityHistoryDataService;

    @Autowired
    private GasLiveDataService gasLiveDataService;
    @Autowired
    private WaterLiveDataService waterLiveDataService;

    @GetMapping(value={"","/"})
    public String showLogInScreen(){
        return "logInScreen";
    }

    @GetMapping("/index")
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

    @GetMapping("/historyDataElectricity/{pageNo}")
    public String getHistoryData(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 36;

        Page<ElectricityHistoryData> page = electricityHistoryDataService.getHistoryData(pageNo, pageSize);
        List<ElectricityHistoryData> listElectricityHistory = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listElectricityHistory", listElectricityHistory);

        return "historyData";
    }
}
