package com.js.amoeba.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.js.amoeba.domain.User;
import com.js.amoeba.domain.UserDetails;
import com.js.amoeba.domain.VerifyToken;
import com.js.amoeba.exception.AmoebaDaoException;

@Repository
public interface UserDao {
public User getUserDetailsByUserName (String username);

	
	public void saveUser(User user);

	public int validateEmail(String emailId);
	public void updateUser(User user);


	public void activateUser(String userName) throws AmoebaDaoException;


	public void deActivateUser(String userName)throws AmoebaDaoException;


	public void updateToken(VerifyToken verifyToken) throws AmoebaDaoException;

	@Select("Select * from users where token=#{token}")
	public VerifyToken verifyToken(String token) throws AmoebaDaoException;


	@Update("Update users set confirmed=#{confirmed} , verification=#{verified} where token =#{token}")
	public void updateConfirmation(@Param("confirmed") Boolean confirmed, @Param("verified") Boolean verified, @Param("token") String token)throws AmoebaDaoException;

	@Select("Select * from users where role != 3")
	public List<User> fetchAllUsers()throws AmoebaDaoException;

	@Select("<script>Select id as userId,firstName,lastName,email,phone as contactNo ,  from users where id in <foreach item='item' collection='userIds' open='(' separator=',' close=')'>#{item}</foreach></script>")
	public List<User> fetchUserByIds(@Param("userIds") List<Integer> userIds) throws AmoebaDaoException;
	
	@Select("Select id as userId,firstName,lastName,email, phone as contactNo , from users where id = #{userId}")
	public UserDetails fetchUserById(@Param("userId") Integer userId) throws AmoebaDaoException;

	@Update("update users set password=#{user.password} , updated_at=NOW() where email=#{user.email}")
	public void updatePassword(@Param("user") User user);
	
}


