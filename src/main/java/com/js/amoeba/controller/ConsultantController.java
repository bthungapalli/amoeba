package com.js.amoeba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.Form;
import com.js.amoeba.domain.Message;
import com.js.amoeba.domain.User;
import com.js.amoeba.exception.AmoebaException;
import com.js.amoeba.service.ConsultantService;
import com.js.amoeba.service.FormService;
import com.js.amoeba.service.MessageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/consultant")
public class ConsultantController {
	
	@Autowired
	private ConsultantService consultantService;
	
	@Autowired
	private MessageService messageService ;
	
	@Autowired
	private FormService formService;
	
	private static final Logger logger=LoggerFactory.getLogger(ConsultantController.class);
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> saveConsultant(@RequestBody Consultant consultant,HttpServletRequest request){
		Map<String,String> map=new HashMap<>();
		
		try{
		SecurityUser securityUser=(SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(securityUser.getUserId());
		int u_Id=securityUser.getUserId();
		consultantService.updateUser(consultant,u_Id);
		System.out.println(consultant.getC_id());
		System.out.println(consultant.getUserId());
		map.put("SUCCESS", "Consultant Details Are Successfully Saved");
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		}catch (Exception e) {
			map.put("FAILED", "Error occured while Storing the Consultant");
			logger.error("Error occured while Saving Consultant::::", e.getMessage());
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public ResponseEntity<?> GetAllConsultants(HttpServletRequest request){
		
		try {
			return new ResponseEntity<List<User>> (consultantService.getAllConsultant(), HttpStatus.OK);
		} catch (AmoebaException e) {
			logger.error("erroe wile fetching All Consultants : : :" +e.getMessage());
			return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/get/{u_id}",method=RequestMethod.GET)
	public ResponseEntity<?> GetConsultants(@PathVariable("u_id") int u_id,HttpServletRequest request) {
		
		try {
			System.out.println(u_id);
			User user=consultantService.getConsultantById(u_id);
			System.out.println(user);
			if(user !=null){
			return new ResponseEntity<> (user, HttpStatus.OK);
			}
			return new ResponseEntity<> ("consultant not found for this id", HttpStatus.OK);
		} catch (AmoebaException e) {
			logger.error("erroe wile getting Consultants : : :" +e.getMessage());
			return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getForms", method=RequestMethod.GET)
	public ResponseEntity<?> getForms(HttpServletRequest request){
		
		try {
			SecurityUser sUser=(SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int u_id=sUser.getUserId();
			System.out.println(u_id);
			User user=consultantService.getConsultantById(u_id);
			return new ResponseEntity<>(consultantService.getForms(user),HttpStatus.OK);
		} catch (AmoebaException e) { 
			logger.error("error getting forms"+e.getMessage());
			return new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(value="/acceptForm/{f_id}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateForm(HttpServletRequest request,  @PathVariable("f_id") int f_id){
		
		Map<String,String> map=new HashMap<String,String>();
		SecurityUser user= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int id=user.getUserId();
		try {
			System.out.println(id);
			consultantService.updateUserForm(id, f_id);
			map.put("success", "your Successfully Accepted Form");
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getting error while updating userform"+e.getMessage());
			map.put("error", "your getting error");
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	} 
	
	@RequestMapping(value="/sendMessage/{to_Id}", method=RequestMethod.POST)
	public ResponseEntity<?> sendMessage(@RequestBody Message message,@PathVariable("to_Id") int to_Id, HttpServletRequest request){
		
		try {
			SecurityUser user= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int from_id=user.getUserId();
			message.setFrom_Id(from_id); 
			Form form=formService.getUserFormById(to_Id);
			message.setTo_Id(form.getUser_id());
			message.setGroup_Id("user:"+from_id+"user:"+form.getUser_id());
			String group_id="user:"+from_id+"user:"+form.getUser_id();
			messageService.sendConsultantMessage(message); 
			return new ResponseEntity<>(messageService.getConsultantMessage(group_id),HttpStatus.OK);
		} catch (AmoebaException e) {
			logger.error("error whhile Sendinng message"+e.getMessage());
			return new ResponseEntity<>("Faild",HttpStatus.OK);
		}
		
	}
	

}
