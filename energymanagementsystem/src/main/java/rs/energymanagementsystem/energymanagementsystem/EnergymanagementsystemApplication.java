package rs.energymanagementsystem.energymanagementsystem;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.*;
import rs.energymanagementsystem.energymanagementsystem.ConfigCustom.security.Password;
import rs.energymanagementsystem.energymanagementsystem.repositories.UserRepository;
import rs.energymanagementsystem.energymanagementsystem.services.*;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
public class EnergymanagementsystemApplication {

	/** ---------------------------------------------------------------------------------------
	 * Main Application Controller
	 * Necessary for managing application business logic with separate APIs.
	 * ---------------------------------------------------------------------------------------
	 * Loading services for:
	 *  -> Electricity Live Data
	 * 	-> Electricity History Data
	 * 	-> Gas Live Data
	 * 	-> Gas History Data
	 * 	-> Water Live Data
	 * 	-> Water History Data
	 * 	-> Devices
	 * 	-> Users
	 * 	-> Alarms
	 * --------------------------------------------------------------------------------------- **/

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
	private UsersService usersService;

	@Autowired
	private AlarmDataService alarmDataService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private  UserRepository userRepository;

	@GetMapping("/login")
	private String showLogInScreen(){
		return "login";
	}

	/** ---------------------------------------------------------------------------------------
	 * For nav style active class is switched with thymeleaf and HttpServletRequest
	 * Active class is changed based on URI provided with mapping API methods
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping({"/","/index","/home"})
	public String showHomePage(HttpServletRequest request, Model model, Principal connectedUser){

		/** Navigation active class object */
		model.addAttribute("request", request);

		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/**
		 * Calculate MAX values
		 * Get date of MAX value
		 * **/
		/** Electricity */
		Integer electricityMaxValue = electricityHistoryDataService.getElectricityMaxValue();
		model.addAttribute("electricityMaxValue", electricityMaxValue);
		Date electricityMaxValueDate = electricityHistoryDataService.getElectricityMaxValueDate();
		model.addAttribute("electricityMaxValueDate", electricityMaxValueDate);

		/** Gas */
		Integer gasMaxValue = gasHistoryDataService.getGasMaxValue();
		model.addAttribute("gasMaxValue", gasMaxValue);
		Date gasMaxValueDate = gasHistoryDataService.getGasMaxValueDate();
		model.addAttribute("gasMaxValueDate", gasMaxValueDate);

		/** Water */
		Integer waterMaxValue = waterHistoryDataService.getWaterMaxValue();
		model.addAttribute("waterMaxValue", waterMaxValue);
		Date waterMaxValueDate = waterHistoryDataService.getWaterMaxValueDate();
		model.addAttribute("waterMaxValueDate", waterMaxValueDate);

		/**
		 * Calculate AVG values
		 * Get date of first and last record
		 * **/
		/** Electricity */
		Integer electricityAvgValue = electricityHistoryDataService.getElectricityAvgValue();
		model.addAttribute("electricityAvgValue", electricityAvgValue);
		Date getElectricityFirstRecordDate = electricityHistoryDataService.getFirstDate();
		model.addAttribute("getElectricityFirstRecordDate", getElectricityFirstRecordDate);
		Date getElectricityLastRecordDate = electricityHistoryDataService.getLastDate();
		model.addAttribute("getElectricityLastRecordDate", getElectricityLastRecordDate);

		/** Gas */
		Integer gasAvgValue = gasHistoryDataService.getGasAvgValue();
		model.addAttribute("gasAvgValue", gasAvgValue);
		Date getGasFirstRecordDate = gasHistoryDataService.getFirstDate();
		model.addAttribute("getGasFirstRecordDate", getGasFirstRecordDate);
		Date getGasLastRecordDate = gasHistoryDataService.getLastDate();
		model.addAttribute("getGasLastRecordDate", getGasLastRecordDate);

		/** Water */
		Integer waterAvgValue = waterHistoryDataService.getWaterAvgValue();
		model.addAttribute("waterAvgValue", waterAvgValue);
		Date getWaterFirstRecordDate = waterHistoryDataService.getFirstDate();
		model.addAttribute("getWaterFirstRecordDate", getWaterFirstRecordDate);
		Date getWaterLastRecordDate = waterHistoryDataService.getLastDate();
		model.addAttribute("getWaterLastRecordDate", getWaterLastRecordDate);

		/**
		 * Calculate MIN values
		 * Get date of MIN value
		 * **/
		/** Electricity */
		Integer electricityMinValue = electricityHistoryDataService.getElectricityMinValue();
		model.addAttribute("electricityMinValue", electricityMinValue);
		Date electricityMinValueDate = electricityHistoryDataService.getElectricityMinValueDate();
		model.addAttribute("electricityMinValueDate", electricityMinValueDate);

		/** Gas */
		Integer gasMinValue = gasHistoryDataService.getGasMinValue();
		model.addAttribute("gasMinValue", gasMinValue);
		Date gasMinValueDate = gasHistoryDataService.getGasMinValueDate();
		model.addAttribute("gasMinValueDate", gasMinValueDate);

		/** Water */
		Integer waterMinValue = waterHistoryDataService.getWaterMinValue();
		model.addAttribute("waterMinValue", waterMinValue);
		Date waterMinValueDate = waterHistoryDataService.getWaterMinValueDate();
		model.addAttribute("waterMinValueDate", waterMinValueDate);

		/**
		 * Get the latest alarm data records
		 * **/
		List<AlarmData> latestAlarmData = alarmDataService.getLatestDate();
		model.addAttribute("latestAlarmData", latestAlarmData);

		/** ---------------------------------------------------------------------------------------
		 * Get Number of active devices
		 * Get Number of inactive devices
		 --------------------------------------------------------------------------------------- **/
		Integer numberOfActiveDevices = devicesService.getNumberOfActiveDevices();
		model.addAttribute("numberOfActiveDevices", numberOfActiveDevices);
		Integer numberOfInactiveDevices = devicesService.getNumberOfInactiveDevices();
		model.addAttribute("numberOfInactiveDevices", numberOfInactiveDevices);

		return "index";
	}

	/** ---------------------------------------------------------------------------------------
	 * Live Data page
	 * Electricity, Water, Gas
	 * Idea is to update database more realistic values in the future.
	 *
	 * Live Data is refreshed every 10s with HTML meta tag (this is solution for exam demonstration)
	 * <meta HTTP-EQUIV="Refresh" CONTENT="10">
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping("/liveData")
	public String getLastData(HttpServletRequest request, Model model, Principal connectedUser){

		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/** Show data */
		List<ElectricityLiveData> lastElectricityData = electricityLiveDataService.getLastData();
		model.addAttribute("lastElectricityData", lastElectricityData);

		List<GasLiveData> lastGasData = gasLiveDataService.getLastData();
		model.addAttribute("lastGasData", lastGasData);

		List<WaterLiveData> lastWaterData = waterLiveDataService.getLastData();
		model.addAttribute("lastWaterData", lastWaterData);

		/** Navigation active class object */
		model.addAttribute("request", request);

		String getCurrentDateTime = getCurrentTimeUsingDate();
		model.addAttribute("getCurrentDateTime", getCurrentDateTime);

		return "liveData";
	}

	/** ---------------------------------------------------------------------------------------
	 * History Data records displayed by pages in following order:
	 * 	- Electricity
	 * 	- Gas
	 * 	- Water
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping("/historyDataElectricity/{pageNo}")
	public String getHistoryData(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model, Principal connectedUser){
		int pageSize = 36;

		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/** Show data */
		Page<ElectricityHistoryData> page = electricityHistoryDataService.getHistoryDataElectricity(pageNo, pageSize);
		List<ElectricityHistoryData> listElectricityHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listElectricityHistory", listElectricityHistory);

		/** Navigation active class object */
		model.addAttribute("request", request);

		String getCurrentDateTime = getCurrentTimeUsingDate();
		model.addAttribute("getCurrentDateTime", getCurrentDateTime);

		return "historyDataElectricity";
	}

	@GetMapping("/historyDataGas/{pageNo}")
	public String getHistoryDataGas(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model, Principal connectedUser){
		int pageSize = 36;

		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/** Show data */
		Page<GasHistoryData> page = gasHistoryDataService.getHistoryDataGas(pageNo, pageSize);
		List<GasHistoryData> listGasHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listGasHistory", listGasHistory);

		/** Navigation active class object */
		model.addAttribute("request", request);

		String getCurrentDateTime = getCurrentTimeUsingDate();
		model.addAttribute("getCurrentDateTime", getCurrentDateTime);

		return "historyDataGas";
	}

	@GetMapping("/historyDataWater/{pageNo}")
	public String getHistoryDataWater(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo, Model model, Principal connectedUser){
		int pageSize = 36;

		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/** Show data */
		Page<WaterHistoryData> page = waterHistoryDataService.getHistoryDataWater(pageNo, pageSize);
		List<WaterHistoryData> listWaterHistory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listWaterHistory", listWaterHistory);

		/** Navigation active class object */
		model.addAttribute("request", request);

		String getCurrentDateTime = getCurrentTimeUsingDate();
		model.addAttribute("getCurrentDateTime", getCurrentDateTime);

		return "historyDataWater";
	}


	/** ---------------------------------------------------------------------------------------
	 * Settings page
	 * To be added in next version.
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping({"/settings"})
	public String showSettingsPage(HttpServletRequest request, Model model){

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "settings";
	}


	/** ---------------------------------------------------------------------------------------
	 * Devices managements section
	 * This part of controller manages basic device CRUD operations
	 * This is an additional control over the API controls that
	 * are available under controllers package
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping("/activeDevices") // GET
	public String getActiveDevices(HttpServletRequest request, Model model){
		List<Devices> activeDevicesList = devicesService.getActiveDevices();
		model.addAttribute("activeDevicesList", activeDevicesList);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "activeDevices";
	}

	@GetMapping("/availableDevices") // GET
	public String getAvailableDevices(HttpServletRequest request, Model model){
		List<Devices> devicesList = devicesService.getAllDevices();
		model.addAttribute("devicesList", devicesList);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "availableDevices";
	}

	@PostMapping("/availableDevices/saveDeviceViaForm") // SAVE
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
		return "redirect:/availableDevices";
	}

	@GetMapping("/availableDevices/newDeviceForm") // NEW FORM
	public String addNewDeviceForm(Model model){
		// Create model attribute to bind form data
		Devices device = new Devices();
		model.addAttribute("device", device);
		return "newDevice";
	}

	@GetMapping("/availableDevices/deviceUpdateForm/{device_id}") // UPDATE, RETURN FORM
	public String deviceUpdateForm(@PathVariable(value = "device_id") Integer device_id, Model model) {

		// get device from the service
		Devices devices = devicesService.getDeviceById(device_id);

		// set device as a model attribute to pre-populate the form
		model.addAttribute("devices", devices);
		model.addAttribute("localDateTime", LocalDateTime.now());

		return "updateDevice";
	}

	@GetMapping("/availableDevices/deleteDevice/{device_id}") // DELETE
	public String deleteDevice(@PathVariable(value = "device_id") Integer device_id) {

		// call delete device method
		this.devicesService.deleteDevice(device_id);
		return "redirect:/availableDevices";
	}

	@GetMapping("/availableDevices/toggleFlag/{device_id}") // CHANGE ACTIVE FLAG
	public String deviceActiveFlag(@PathVariable(value = "device_id") Integer device_id){
		devicesService.deviceActiveFlag(device_id);
		return "redirect:/availableDevices";
	}

	/** ---------------------------------------------------------------------------------------
	 * Users managements section
	 * This part of controller manages basic user CRUD operations
	 * This is an additional control over the API controls that
	 * are available under controllers package
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping("/users") // GET users list
	public String getUsers(HttpServletRequest request, Model model){
		List<User> usersList = usersService.getAllUsers();
		model.addAttribute("usersList", usersList);

		/* Navigation active class object */
		model.addAttribute("request", request);

		return "users";
	}

	@PostMapping("/users/saveUserViaForm") // SAVE
	public String saveUserViaForm(@ModelAttribute(value = "user") User user, Model model){

		if(usersService.existsByUsername(user.getUsername())){
			throw new DuplicateKeyException("Username already exists.");
		}
		if(usersService.existsByEmail(user.getEmail())){
			throw new DuplicateKeyException("Email already exists.");
		}

		user.setPassword(Password.hashPassword(user.getPassword()));
		usersService.createUser(user);
		return "redirect:/users";
	}

	@GetMapping("/users/newUserForm") // OPEN NEW FORM
	public String addNewUserForm(Model model){
		// Create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);

		List<Role> roles = (List<Role>) roleService.findAll();
		model.addAttribute("roles", roles);

		return "newUser";
	}
	public String updateUserName;
	public String updateUserEmail;
	@GetMapping("/users/userUpdateForm/{id}") // UPDATE, RETURN FORM
	public String userUpdateForm(@PathVariable(value = "id") Long id, Model model) {

		// get user from the service
		User user = usersService.getUserById(id);
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);

		// get roles from repository
		List<Role> roles = (List<Role>) roleService.findAll();
		model.addAttribute("roles", roles);

		updateUserName = user.getUsername();
		updateUserEmail = user.getEmail();

		return "updateUser";
	}
	@PostMapping("/users/updateUserViaForm") // This part should be refactored to match update default class above this method
	public String updateUserViaForm(@ModelAttribute(value = "user") User user){

		if(usersService.existsByUsername(user.getUsername()) && !updateUserName.equals(user.getUsername())){
			throw new DuplicateKeyException("Username already exists.");
		}
		if(usersService.existsByEmail(user.getEmail()) && !updateUserEmail.equals(user.getEmail())){
			throw new DuplicateKeyException("Email already exists.");
		}

		usersService.updateUser(user, user.getId());
		//user.setPassword(Password.hashPassword(user.getPassword()));
		//usersService.saveUser(user);
		return "redirect:/users";
	}

	@GetMapping("/users/deleteUser/{id}") // DELETE
	public String deleteUser(@PathVariable(value = "id") Long id, Model model, User user, Principal connectedUser) {
/** Old method -> changed with Principal to clean code.
		// Show currently logged-in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = authentication.getName();
		model.addAttribute("loggedUser", loggedUser);
**/

		// call delete user method
		User userToDelete = usersService.getUserById(id);
		if("root".equalsIgnoreCase(userToDelete.getUsername())) {
			System.out.println("Root user can not be deleted.");
			throw new UnsupportedOperationException("Root user can not be deleted.");
		} else if(connectedUser.getName().equals(userToDelete.getUsername())){
			System.out.println("Currently logged-in user can not be deleted.");
			throw new UnsupportedOperationException("Currently logged-in user can not be deleted.");
		}else { // delete user if it's not root or currently logged in.
			System.out.println("User " + user + " has been deleted.");
			this.usersService.deleteUser(id);
		}
		return "redirect:/users";
	}
	@GetMapping("/users/toggleFlag/{id}") // CHANGE ACTIVE FLAG
	public String userActiveFlag(@PathVariable(value = "id") Long id, Principal connectedUser){

		User userToChange = usersService.getUserById(id);
		if("root".equalsIgnoreCase(userToChange.getUsername())){
			System.out.println("Root user can not be deactivated.");
			throw new UnsupportedOperationException("Root user can not be deactivated.");
		} else if(connectedUser.getName().equals(userToChange.getUsername())){
			System.out.println("Currently logged-in user can not be deactivated.");
			throw new UnsupportedOperationException("Currently logged-in user can not be deactivated.");
		} else { // deactivate user
			System.out.println("User " + userToChange.getUsername() + " has been deactivated.");
			usersService.userActiveFlag(id);
		}
		return "redirect:/users";
	}

	@GetMapping("/changePasswordForm")
	public String changePasswordForm(Model model, Principal connectedUser, Passwords passwords){

		model.addAttribute("passwords", passwords);

		return "changePasswordForm";
	}

	@PostMapping("/changePasswordLogic")
	public String changeAccountPassword(Principal connectedUser, Passwords passwords) throws Exception {

		User user = userRepository.findByUsername(connectedUser.getName());

		String oldPassUser = user.getPassword();
		String oldPassCheck = passwords.getOldPassword();
		String newPass = passwords.getNewPassword();
		String repeatNewPass = passwords.getRepeatNewPassword();

		System.out.println("Before loop: " + oldPassUser);
		System.out.println("Before loop: " + oldPassCheck);
		System.out.println("Before loop: " + newPass);
		System.out.println(user);

		if(repeatNewPass.equals(newPass)){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			/** Logic for password check and change */
			if(!encoder.matches(oldPassCheck,oldPassUser)){
				throw new Exception("Entered current password does not match with " + connectedUser.getName() + "'s password.");
			} else {
				System.out.println("Hash loop: " + oldPassUser);
				System.out.println("Hash loop: " + oldPassCheck);
				user.setPassword(Password.hashPassword(newPass));
				usersService.updateUser(user, user.getId());
			}
		} else throw new Exception("Entered new password does not match. Check your typing.");

		return "redirect:/users";
	}

	/** ---------------------------------------------------------------------------------------
	 * Get history of all alarms
	 * --------------------------------------------------------------------------------------- **/
	@GetMapping("/alarms") // get all alarms
	public String getAllAlarms(HttpServletRequest request, Model model, Principal connectedUser){
		/** Display connected user on header */
		model.addAttribute("connectedUser", connectedUser.getName());

		/** Get alarms data model */
		List<AlarmData> getAllAlarms = alarmDataService.getAllAlarmData();
		model.addAttribute("getAllAlarms", getAllAlarms);

		/** Navigation active class object */
		model.addAttribute("request", request);

		return "alarms";
	}

	@GetMapping("/ackFlag/{alarm_id}") // CHANGE ACTIVE FLAG
	public String alarmAckFlag(@PathVariable(value = "alarm_id") Integer alarm_id){
		alarmDataService.alarmAckFlag(alarm_id);
		return "redirect:/alarms";
	}


	/** ---------------------------------------------------------------------------------------
	 * Get current date and time
	 * --------------------------------------------------------------------------------------- **/
	public static @NotNull String getCurrentTimeUsingDate() {
		Date date = new Date();
		String strDateFormat = "yyyy/MM/dd  HH:mm";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate= dateFormat.format(date);

		return formattedDate;
	}


	/** ---------------------------------------------------------------------------------------
	 * Standard main class
	 * --------------------------------------------------------------------------------------- **/
	public static void main(String[] args) {

		SpringApplication.run(EnergymanagementsystemApplication.class, args);
	}

}
