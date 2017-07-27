package com.js.amoeba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.amoeba.config.MailUtil;
import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.domain.Form;
import com.js.amoeba.domain.Message;
import com.js.amoeba.exception.AmoebaException;
import com.js.amoeba.service.FormService;
import com.js.amoeba.service.MessageService;


@RestController
public class FormController {
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private MessageService messageService;
	
	private static final Logger logger=LoggerFactory.getLogger(FormController.class);
	
	@RequestMapping(value="/formSub",method=RequestMethod.POST)
	public ResponseEntity<?> saveForm(@RequestBody Form form, HttpServletRequest request){
		Map<String,String> map=new HashMap<>();
		try {
		SecurityUser securityUser= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		form.setUser_id(securityUser.getUserId());
		formService.saveForm(form);
		//map.put("email", securityUser.getEmail());
		mailUtil.userFormSubMail(securityUser);
		
		
		map.put("success", "your Form is successfull submited");
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("getting error while submitting form ::" +e.getMessage());
			map.put("Failure", "getting error while submitting form ");
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@RequestMapping(value="/getForm/{f_id}",method=RequestMethod.GET)
	public ResponseEntity<?> getUserFormById(@PathVariable("f_id") int f_id, HttpServletRequest request){
		
		try {
			Form  form=formService.getUserFormById(f_id);
			if(form !=null)
			return new ResponseEntity<> (form, HttpStatus.OK);
			return new ResponseEntity<> ("form not found for this id", HttpStatus.OK);
		} catch (AmoebaException e) {
			logger.error("erroe while getting form : : :" +e.getMessage());
			return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/*@RequestMapping(value="/getForm",method=RequestMethod.GET)
	public ResponseEntity<?> getFormById(HttpServletRequest request){
		
		try {
			SecurityUser  user= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return new ResponseEntity<List<Form>> (formService.getFormById(user.getUserId()), HttpStatus.OK);
			
		} catch (AmoebaException e) {
			logger.error("erroe wile getting form : : :" +e.getMessage());
			return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}*/
	
	@RequestMapping(value="/get/{userId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getFormById(@PathVariable("userId") int userId,HttpServletRequest request){
		
		try {
			System.out.println(userId);
			return new ResponseEntity<List<Form>> (formService.getFormById(userId), HttpStatus.OK);
			
		} catch (AmoebaException e) {
			logger.error("erroe wile getting form : : :" +e.getMessage());
			return new ResponseEntity<>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@RequestMapping(value="/sendMessage/{to_Id}", method=RequestMethod.POST)
	public ResponseEntity<?> sendMessage(@RequestBody Message message,@PathVariable("to_Id") int to_Id, HttpServletRequest request){
		
		try {
			SecurityUser user= (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			int from_id=user.getUserId();
			message.setFrom_Id(from_id); 
			message.setTo_Id(to_Id);
			String group_id="user:"+to_Id+"user:"+from_id;
			message.setGroup_Id(group_id);
			messageService.sendConsultantMessage(message);
			
			return new ResponseEntity<>(messageService.getConsultantMessage(group_id),HttpStatus.OK);
		} catch (AmoebaException e) {
			logger.error("error while Sendinng message"+e.getMessage());
			return new ResponseEntity<>("Faild",HttpStatus.OK);
		}
		
	}

}
