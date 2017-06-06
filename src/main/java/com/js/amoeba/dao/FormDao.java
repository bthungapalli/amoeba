package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.Form;
import com.js.amoeba.exception.AmoebaException;



@Repository
public interface FormDao {
	
	//@Insert("insert into userform values (age=#{age},weight=#{weight},height=#{height},specialization=#{specialization},mainCat=#{mainCat},subCat=#{subCat},user_id=#{user_id},status=#{status},c_id=#{c_id})")
	public void saveForm(Form from) throws Exception;
	
	public Form getUserFormById(@Param("f_id") Integer f_id) throws AmoebaException;
	
	public List<Form> getForms() throws AmoebaException;
	
	public List<Form> getFormById(@Param("user_id") Integer user_id) throws AmoebaException;
	

}
