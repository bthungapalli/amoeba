 package com.js.amoeba.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.js.amoeba.config.SecurityUser;
import com.js.amoeba.domain.Consultant;
import com.js.amoeba.domain.User;
import com.js.amoeba.domain.VerifyToken;
import com.js.amoeba.exception.AmoebaException;







@Repository
public interface UserDao {
	
	public User getUserDetailsByUserName (String username);
	
	public void saveUser(User user) throws Exception;
	
	public int validateEmail(String emailId);
	
	public void updateUser(User user);
	
	
	
	public void activateUser(String userName) throws AmoebaException;


	public void deActivateUser(String userName)throws AmoebaException;


	public void updateToken(VerifyToken verifyToken) throws AmoebaException;

	@Select("Select * from users where token=#{token}")
	public VerifyToken verifyToken(String token) throws AmoebaException;

	@Update("Update users set confirmed=#{confirmed} , verification=#{verified} where token =#{token}")
	public void updateConfirmation(@Param("confirmed") Boolean confirmed, @Param("verified") Boolean verified, @Param("token") String token)throws AmoebaException;

	@Select("Select * from users where role != 2")
	public List<User> fetchAllUsers()throws AmoebaException;

	@Select("Select * from users where userId = #{userId}")
	public User fetchUserById(@Param("userId") Integer userId) throws AmoebaException;

	@Update("update users set password=#{user.password} , updatedAt=NOW() where email=#{user.email}")
	public void updatePassword(@Param("user") User user);
	
	@Update("update users set experience=#{consultant.experience}, mainCat=#{consultant.mainCat},specialization=#{consultant.specialization}, subCat=#{consultant.subCat} where email=#{securityUser.email} AND role = 1")
	public void updateUser1(@Param("securityUser")SecurityUser securityUser, @Param("consultant")Consultant consultant);

}
