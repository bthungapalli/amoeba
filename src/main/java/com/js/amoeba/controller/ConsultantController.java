package com.js.amoeba.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.exception.AmoebaDaoException;
import com.js.amoeba.service.ConsultantService;

@RestController
@RequestMapping("/consultant")
public class ConsultantController {
	 @Autowired
	 private ConsultantService consultantService;
	 
	 private static final Logger logger=LoggerFactory.getLogger(ConsultantController.class);
	 
	 @RequestMapping(method=RequestMethod.POST)
	 public ResponseEntity<?> saveConsultant(@RequestBody Consultant consultant,HttpServletRequest request){
	  Map<String,String> map=new HashMap<>();
	  
	  try{
		  SecurityUser securityUser=(SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  System.out.println(12);
		  System.out.println(securityUser.getId());
		  consultant.setuId(securityUser.getId());
		
	  consultantService.saveConsultant(consultant);
	  System.out.println(consultant.getuId());
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
		  logger.error("success fetching all consultants::");
	   return new ResponseEntity<List<Consultant>>(consultantService.getAllConsultant(),HttpStatus.OK);
	  } 
	  catch (AmoebaDaoException e) {
	   logger.error("error wile fetching All Consultants : : :" +e.getMessage());
	   return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 }
	 
	 @RequestMapping(value="/get/{c_id}",method=RequestMethod.GET)
	 public ResponseEntity<?> GetConsultants(@PathVariable("c_id") int c_id,HttpServletRequest request) {
	  
	  try {
	   Consultant  consultant=consultantService.getConsultantById(c_id);
	   if(consultant !=null)
	   return new ResponseEntity<> (consultant, HttpStatus.OK);
	   return new ResponseEntity<> ("consultant not found for this id", HttpStatus.OK);
	  } catch (AmoebaDaoException e) {
	   logger.error("error wile getting Consultants : : :" +e.getMessage());
	   return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	 }
}
