package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.UserForm;
import com.js.amoeba.exception.AmoebaDaoException;



@Repository
public interface UserFormDao {

	public void saveForm(UserForm userform) throws Exception; 
	
	/*@Select("select * from userform f,users u where f.uId=u.id") 
	public List<UserForm>fetchAllFormById()throws AmoebaDaoException;
	
	@Select("select *from userform f,consultant c where f.c_id=c_id") 
	public List<UserForm>fetchAllById()throws AmoebaDaoException;*/
	

	//@select("c . * , f.weight FROM consultant  c, userform f  WHERE c.formId = f.formId")
}
