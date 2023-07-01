package rs.energymanagementsystem.energymanagementsystem;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.*;
import rs.energymanagementsystem.energymanagementsystem.services.*;

import java.time.LocalDateTime;
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

	@Autowired
	private DevicesService devicesService;

	@Autowired
	private CustomUserDetailsService usersService;

	@GetMapping("/login")
	public String showLogInScreen(){
		return "login";
	}

	/**
	 * For nav style active class is switched with thymeleaf and HttpServletRequest
	 * Active class is changed based on link provided with mapping methods
	 * **/
	@GetMapping({"/","/index","/home"})
	public String showHomePage(HttpServletRequest request, Model model){

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "index";
	}
	@GetMapping("/liveData")
	public String getLastData(HttpServletRequest request, Model model){
		List<ElectricityLiveData> lastElectricityData = electricityLiveDataService.getLastData();
		model.addAttribute("lastElectricityData", lastElectricityData);

		List<GasLiveData> lastGasData = gasLiveDataService.getLastData();
		model.addAttribute("lastGasData", lastGasData);

		List<WaterLiveData> lastWaterData = waterLiveDataService.getLastData();
		model.addAttribute("lastWaterData", lastWaterData);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "liveData";
	}

	@GetMapping("/historyDataElectricity/{pageNo}")
	public String getHistoryData(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model){
		int pageSize = 36;

		Page<ElectricityHistoryData> page = electricityHistoryDataService.getHistoryDataElectricity(pageNo, pageSize);
		List<ElectricityHistoryData> listElectricityHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listElectricityHistory", listElectricityHistory);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "historyDataElectricity";
	}

	@GetMapping("/historyDataGas/{pageNo}")
	public String getHistoryDataGas(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model){
		int pageSize = 36;

		Page<GasHistoryData> page = gasHistoryDataService.getHistoryDataGas(pageNo, pageSize);
		List<GasHistoryData> listGasHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listGasHistory", listGasHistory);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "historyDataGas";
	}

	@GetMapping("/historyDataWater/{pageNo}")
	public String getHistoryDataWater(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model){
		int pageSize = 36;

		Page<WaterHistoryData> page = waterHistoryDataService.getHistoryDataWater(pageNo, pageSize);
		List<WaterHistoryData> listWaterHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listWaterHistory", listWaterHistory);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "historyDataWater";
	}

	@GetMapping({"/settings"})
	public String showSettingsPage(HttpServletRequest request, Model model){

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "settings";
	}


	/**
	 * Devices managements section
	 * This part of controller manages basic device CRUD operations
	 * This is an additional control over the API controls that
	 * are available under controllers package
	 * **/
	@GetMapping("/devices")
	public String getDevices(HttpServletRequest request, Model model){
		List<Devices> devicesList = devicesService.getAllDevices();
		model.addAttribute("devicesList", devicesList);

		List<Devices> activeDevicesList = devicesService.getActiveDevices();
		model.addAttribute("activeDevicesList", activeDevicesList);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "devices";
	}

	@PostMapping("/saveDeviceViaForm")
	public String saveDeviceViaForm(@ModelAttribute(value = "devices") Devices device){
		//Assign default value for active_flag
		if(device.getActive_flag() == null){
			device.setActive_flag(false); // default value set to false (same as in SQL)
		}

		if(device.getDescription() == null || device.getDescription() == ""){
			device.setDescription("missing description entry"); // autofill message
		}
		// save device to database repository
		devicesService.saveDevice(device);
		return "redirect:/devices";
	}

	@GetMapping("/newDeviceForm")
	public String addNewDeviceForm(Model model){
		// Create model attribute to bind form data
		Devices device = new Devices();
		model.addAttribute("device", device);
		return "newDevice";
	}

	@GetMapping("/deviceUpdateForm/{device_id}")
	public String deviceUpdateForm(@PathVariable(value = "device_id") Integer device_id, Model model) {

		// get device from the service
		Devices devices = devicesService.getDeviceById(device_id);

		// set device as a model attribute to pre-populate the form
		model.addAttribute("devices", devices);
		model.addAttribute("localDateTime", LocalDateTime.now());

		return "updateDevice";
	}

	@GetMapping("/deleteDevice/{device_id}")
	public String deleteDevice(@PathVariable(value = "device_id") Integer device_id) {

		// call delete device method
		this.devicesService.deleteDevice(device_id);
		return "redirect:/devices";
	}

	@GetMapping("/toggleFlag/{device_id}")
	public String deviceActiveFlag(@PathVariable(value = "device_id") Integer device_id){
		devicesService.deviceActiveFlag(device_id);
		return "redirect:/devices";
	}

	/**
	 * Users managements section
	 * This part of controller manages basic user CRUD operations
	 * This is an additional control over the API controls that
	 * are available under controllers package
	 * **/
	@GetMapping("/users")
	public String getUsers(HttpServletRequest request, Model model){
		List<User> usersList = usersService.getAllUsers();
		model.addAttribute("usersList", usersList);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "users";
	}

	@PostMapping("/saveUserViaForm")
	public String saveUserViaForm(@ModelAttribute(value = "user") User user){
		// save user to database repository
		usersService.saveUser(user);
		return "redirect:/users";
	}

	@GetMapping("/newUserForm")
	public String addNewUserForm(Model model){
		// Create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@GetMapping("/userUpdateForm/{id}")
	public String userUpdateForm(@PathVariable(value = "id") Long id, Model model) {

		// get device from the service
		User user = usersService.getUserById(id);

		// set device as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "updateUser";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id) {

		// call delete user method
		this.usersService.deleteUser(id);
		return "redirect:/users";
	}


	public static void main(String[] args) {
		SpringApplication.run(EnergymanagementsystemApplication.class, args);
	}

}
