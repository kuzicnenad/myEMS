package rs.energymanagementsystem.energymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.energymanagementsystem.energymanagementsystem.entities.*;
import rs.energymanagementsystem.energymanagementsystem.services.*;

import java.util.List;

@Controller
@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
public class EnergymanagementsystemApplication {

	@Autowired
	private ElectricityLiveDataService electricityLiveDataService;

	@Autowired
	private ElectricityHistoryDataService electricityHistoryDataService;

	@Autowired
	private GasLiveDataService gasLiveDataService;

	@Autowired
	private GasHistoryDataService gasHistoryDataService;

	@Autowired
	private WaterLiveDataService waterLiveDataService;

	@Autowired
	private WaterHistoryDataService waterHistoryDataService;

	@GetMapping("/login")
	public String showLogInScreen(){
		return "login";
	}

	@GetMapping({"/","/index","/home"})
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

		Page<ElectricityHistoryData> page = electricityHistoryDataService.getHistoryDataElectricity(pageNo, pageSize);
		List<ElectricityHistoryData> listElectricityHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listElectricityHistory", listElectricityHistory);

		return "historyDataElectricity";
	}

	@GetMapping("/historyDataGas/{pageNo}")
	public String getHistoryDataGas(@PathVariable(value = "pageNo") int pageNo, Model model){
		int pageSize = 36;

		Page<GasHistoryData> page = gasHistoryDataService.getHistoryDataGas(pageNo, pageSize);
		List<GasHistoryData> listGasHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listGasHistory", listGasHistory);

		return "historyDataGas";
	}

	@GetMapping("/historyDataWater/{pageNo}")
	public String getHistoryDataWater(@PathVariable(value = "pageNo") int pageNo, Model model){
		int pageSize = 36;

		Page<WaterHistoryData> page = waterHistoryDataService.getHistoryDataWater(pageNo, pageSize);
		List<WaterHistoryData> listWaterHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listWaterHistory", listWaterHistory);

		return "historyDataWater";
	}

	@GetMapping({"/settings"})
	public String showSettingsPage(){
		return "settings";
	}


	public static void main(String[] args) {
		SpringApplication.run(EnergymanagementsystemApplication.class, args);
	}

}
