package com.js.amoeba.controller;



import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.js.amoeba.config.MailUtil;
import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.constants.AmoebaConstants;
import com.js.amoeba.domain.NewPassword;
import com.js.amoeba.domain.User;
import com.js.amoeba.domain.VerifyToken;
import com.js.amoeba.exception.AmoebaException;
import com.js.amoeba.service.UserService;







@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	private Map<String, Object> loginMap;
	
	@Autowired
	private MailUtil mailUtil;

	private final static String REG_CONFIRMATION_LINK = "/registrationConfirmation?token=";

	@Value("${contextPath}")
	private String contextPath;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private final static String REGISTRATION = "/registration";

	
	
	@RequestMapping(value = REGISTRATION, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveUser(@RequestBody User user, HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		try {
			userService.saveUser(user);
			updateToken(user, request);
			map.put("success", AmoebaConstants.REGISTRATION_SUCCESS_LINK);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map.put("FAILED", AmoebaConstants.REGISTRATION_ERROR);
			logger.error("Error occured while registration::::", e);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/updateToken", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmailToken(@RequestParam("email") String email, HttpServletRequest request) {
		User user = userService.getUserDetailsByUserName(email);
		Map<String, String> map = new HashMap<>();
		if (!user.isConfirmed()) {
			try {
				updateToken(user, request);
				map.put("success", AmoebaConstants.REGISTRATION_SUCCESS_LINK);
			} catch (AmoebaException | MessagingException e) {
				map.put("FAILED", "Problem while updating the Token. Please try after sometime");
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		map.put("success", "User already confirmed. Please contact admin for more details");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	/**
	 * @param user
	 * @param request
	 * @throws AmoebaDaoException
	 * @throws MessagingException
	 */
	private void updateToken(User user, HttpServletRequest request) throws AmoebaException, MessagingException {

		String token = UUID.randomUUID().toString();
		VerifyToken verifyToken = new VerifyToken(token, user.getRole(), user);
		userService.updateToken(verifyToken);
		String confirmUrl = contextPath + "/sendRedirect?token=" + token;
		logger.info("Request URL ::", confirmUrl);
		mailUtil.sendMail(user, confirmUrl);
	}

	@RequestMapping(value = "/sendRedirect", method = RequestMethod.GET)
	public void sendRedirect(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
		response.sendRedirect(contextPath + "/#/registrationConfirmation?token=" + token);
	}

	@RequestMapping(value = "/registration/registrationConfirmation", method = RequestMethod.GET)
	public ResponseEntity<?> confirmRegistration(HttpServletRequest request, @RequestParam("token") String token) {
		Map<String, Object> map = new HashMap<>();
		try {
			VerifyToken verificationToken = userService.verifyToken(token);
			if (verificationToken == null) {
				map.put("FAILED", "USER NOT FOUND");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Calendar cal = Calendar.getInstance();
			if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				map.put("FAILED", "Token Expired");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (verificationToken.getRole() == 0) {
				userService.updateConfirmation(Boolean.TRUE, Boolean.TRUE, token);
			} else {
				userService.updateConfirmation(Boolean.TRUE, Boolean.FALSE, token);
			}
			map.put("success", AmoebaConstants.REGISTRATION_CONFIRMATION_SUCCESS);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (AmoebaException vre) {
			map.put("FAILED", vre.getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> user(Principal user, HttpServletRequest request) {
		try {
			loginMap = new HashMap<>();
			SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			System.out.println(securityUser.getUsername());
			if (!securityUser.isConfirmed())
				return new ResponseEntity<List<String>>(
						new ArrayList<String>(
								Arrays.asList("Email Not Confirm. Please verify your email for confirmation link.")),
						HttpStatus.OK);
			if (!securityUser.isVerification())
				return new ResponseEntity<List<String>>(
						new ArrayList<String>(
								Arrays.asList("Account Deactivated . Please contanct Admin to activate your account.")),
						HttpStatus.OK);
			
			loginMap.put("user", securityUser);
			HttpSession session = request.getSession();
			session.setAttribute(session.getId(), loginMap);
			return new ResponseEntity<Map<String, Object>>(loginMap, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed to connect", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		loginMap = null;
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/emailValidation", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> validateEmail(@RequestParam String emailId) {
		if (userService.validateEmail(emailId) > 0) {
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("alreadyExist")),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("newEmailId")), HttpStatus.OK);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public ResponseEntity<?> verifyUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		loginMap = (Map<String, Object>) session.getAttribute(session.getId());
		if (loginMap != null) {
			
				SecurityUser securityUser = (SecurityUser) loginMap.get(AmoebaConstants.USER_OBJECT);
				loginMap.put(AmoebaConstants.USER_OBJECT, securityUser);
				return new ResponseEntity<Map<String, Object>>(loginMap, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<String>(AmoebaConstants.INVALID_USER, HttpStatus.UNAUTHORIZED);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateProfile(@ModelAttribute User userdetails, HttpServletRequest request,
			HttpSession session) throws MessagingException, IOException {
		HttpSession userSession = request.getSession(false);
		if (userSession != null) {
			loginMap = (Map<String, Object>) session.getAttribute(session.getId());
			SecurityUser securityUser = (SecurityUser) loginMap.get(AmoebaConstants.USER_OBJECT);
			userdetails.setUserId(securityUser.getUserId());
			userdetails.setEmail(securityUser.getEmail());
			Map<String, Object> map = new HashMap<>();
			userService.updateUser(userdetails);
			securityUser = new SecurityUser(userdetails);

			map.put(AmoebaConstants.USER_OBJECT, securityUser);

			return map;
		} else {
			return (Map<String, Object>) new ResponseEntity<String>(AmoebaConstants.INVALID_USER,
					HttpStatus.UNAUTHORIZED);
		}
	}

	@PreAuthorize("hasRole(ADMIN)")
	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public ResponseEntity<?> activateUser(@RequestParam("username") String userName) {
		try {
			userService.activateUser(userName);
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("success")), HttpStatus.OK);
		} catch (AmoebaException vre) {
			logger.error("Error occured while activating user ", vre.getMessage());
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("FAILED")),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole(ADMIN)")
	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public ResponseEntity<?> deActivateUser(@RequestParam("username") String userName) {
		try {
			userService.deActivateUser(userName);
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("success")), HttpStatus.OK);
		} catch (AmoebaException vre) {
			logger.error("Error occured while activating user ", vre.getMessage());
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList("FAILED")),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//@PreAuthorize("hasRole(ADMIN)")
	@RequestMapping(value = "/fetchAllUsers", method = RequestMethod.GET)
	public ResponseEntity<?> fetchUsers(HttpServletRequest request) {
		try {
			return new ResponseEntity<List<User>>(userService.fetchAllUsers(), HttpStatus.OK);
		} catch (AmoebaException vre) {
			logger.error("Problem while fetching the users :", vre.getMessage());
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/byId/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> fetchUserById(@PathVariable("id") int id, HttpServletRequest request) {
		try {
			return new ResponseEntity<User>(userService.fetchUserById(id), HttpStatus.OK);
		} catch (AmoebaException vre) {
			logger.error("Problem while fetching the users :", vre.getMessage());
			return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<?> getNewpassword(@RequestBody String email) throws MessagingException, AmoebaException {
		Map<String, String> model = new HashMap<>();
		JSONObject jsonObject = new JSONObject(email);
		String inputEmail = jsonObject.getString("email");
		String registered = userService.getNewPassword(inputEmail);
		if (registered.equalsIgnoreCase("Registed")) {
			model.put("status", "success");
		} else {
			model.put("status", "You are not Registred User");
		}
		return new ResponseEntity<Map<String, String>>(model, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody String password)
			throws AmoebaException, MessagingException {
		User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, String> model = new HashMap<>();
		if (securityUser != null) {
			JSONObject jsonObject = new JSONObject(password);
			String newPassword = jsonObject.getString("password");
			userService.updatePassword(securityUser, newPassword);
			model.put("message", "Password Changed successfully");
			return new ResponseEntity<Map<String, String>>(model, HttpStatus.OK);
		}

		model.put("message", "Not registerd User");
		return new ResponseEntity<Map<String, String>>(model, HttpStatus.UNAUTHORIZED);
	}
	

}
