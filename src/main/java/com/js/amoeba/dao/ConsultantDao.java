package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.User;
import com.js.amoeba.domain.UserDetails;
import com.js.amoeba.exception.AmoebaDaoException;

@Repository
public interface ConsultantDao {
	
	public void saveConsultant(Consultant consultant) throws Exception; 
	
	@Select("select * from consultant c,users u where c.uId=u.id") 
	public List<Consultant>fetchAllConsultantById()throws AmoebaDaoException;
	
	@Select("Select * from consultant where c_id=#{c_id} ")
	public Consultant fetchConsultantById(@Param("c_id") Integer c_id)throws AmoebaDaoException;
}

