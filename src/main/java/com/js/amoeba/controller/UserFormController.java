package com.js.amoeba.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.js.amoeba.config.SecurityUser;

import com.js.amoeba.domain.UserForm;
import com.js.amoeba.service.UserFormService;


@RestController
public class UserFormController {
	
	 @Autowired
	 private UserFormService userformService;
	 
	 private static final Logger logger=LoggerFactory.getLogger(UserFormController.class);

	 @RequestMapping( value="/form",method=RequestMethod.POST)
	 public ResponseEntity<?> saveForm(@RequestBody UserForm userform,HttpServletRequest request){
	  Map<String,String> map=new HashMap<>();
	  
	  try{
		  SecurityUser securityUser=(SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  System.out.println(12);
		  System.out.println(securityUser.getId());
		  userform.setuId(securityUser.getId());
		
	  userformService.saveForm(userform);
	  System.out.println(userform.getuId());
	  map.put("SUCCESS", "Form Details Are Successfully Saved");
	  return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	  }catch (Exception e) {
	   map.put("FAILED", "Error occured while Storing the form");
	   logger.error("Error occured while Saving form::::", e.getMessage());
	   return new ResponseEntity<Map<String,String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
	  
	 }
}
