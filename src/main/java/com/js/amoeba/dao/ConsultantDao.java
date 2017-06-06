package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.Form;
import com.js.amoeba.domain.User;
import com.js.amoeba.exception.AmoebaException;



@Repository

public interface ConsultantDao {
	
	public void saveConsultant(Consultant consultent) throws Exception; 
	
	@Update("update users set experience=#{consultant.experience} , spec_Id=#{consultant.spec_Id}, mainCat_Id=#{consultant.mainCat_Id},subSpec_Id=#{consultant.subSpec_Id}, updatedAt=NOW() where userId=#{u_Id} and role=1")
	public void updateUser(@Param("consultant") Consultant consultant, @Param("u_Id") Integer u_Id) throws Exception;
	
	
	@Select("Select * from users   where role=1 ")
	public List<User> fetchAllConsultantById() throws AmoebaException;
	
	
	public User fetchConsultantById(@Param("u_id") Integer u_id) throws AmoebaException;
	
	
	public List<User> getForms(@Param("user") User user) throws AmoebaException;
	
	public List<Form> getUser(int userId);
	
	@Update("update userform set c_id=#{u_id}, status=1  where f_id=#{f_id}")
	public void updateForm(@Param("u_id") Integer u_id, @Param("f_id") Integer f_id)throws Exception;


}
