package com.cafe.proejct.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.juli.logging.Log;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cafe.proejct.JWT.CustomerUserDetailsService;
import com.cafe.proejct.JWT.JwtFilter;
import com.cafe.proejct.JWT.JwtUtil;
import com.cafe.proejct.POJO.User;
import com.cafe.proejct.constents.CafeConstants;
import com.cafe.proejct.dao.UserDao;
import com.cafe.proejct.service.UserService;
import com.cafe.proejct.utils.EmailUtils;
import com.cafe.proejct.utils.cafeutils;
import com.cafe.proejct.wrapper.UserWrapper;
import com.google.common.base.Strings;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	UserDao userDao;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	EmailUtils  emailUtils;
	
	
	
	
	private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		System.out.println("cfgvhbjnm");
		log.info("INSDIE SIGNUP {}" , requestMap);
		try {
		if(validateSignUpMap(requestMap)) {
			System.out.println("dbsjfd");
			User user = userDao.findByEmailId(requestMap.get("email"));
			if(Objects.isNull(user)) {
				userDao.save(getUserFromMap(requestMap));
				return cafeutils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
				
			}else {
				return cafeutils.getResponseEntity("email already exist" , HttpStatus.BAD_REQUEST);
			}
			
		}else {
			return cafeutils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.INVALID_DATA,HttpStatus.BAD_REQUEST );
	}
	
	private boolean validateSignUpMap(Map<String , String> requestMap) {
		if(requestMap.containsKey("name") && requestMap.containsKey("contactnumber")
		      && requestMap.containsKey("email") && requestMap.containsKey("password"))
		{
			return true;
		}else {
			return false;
		}
	}

	private User getUserFromMap(Map<String , String > requestMap) {
		User user = new User();
	    user.setName(requestMap.get("name"));
	    user.setContactNumber(requestMap.get("contactnumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		
		return user;
				
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		log.info("Inside Login");
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email") , requestMap.get("password"))
		);
		if(auth.isAuthenticated()) {
			if(customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
				return new ResponseEntity<String>("{\"token\":\""+
						jwtUtil.generateToken(customerUserDetailsService.getUserDetails().getEmail(), 
								customerUserDetailsService.getUserDetails().getRole()) + "\"}" ,
				HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval"+"\"}" ,
						HttpStatus.BAD_REQUEST );
			}
		}
			
			
	  }catch (Exception ex) {
			log.error("{}" , ex);
	  }
		return new ResponseEntity<String>("{\"message\":\""+"Bad Credentailas"+"\"}" ,
				HttpStatus.BAD_REQUEST );
		
	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
			if(jwtFilter.isAdmin()) {
				return new ResponseEntity<>(userDao.getAllUser() , HttpStatus.OK);
				
			}else {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<> (new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
			  Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
			  if(!optional.isEmpty()) {
				 userDao.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
				 sendMailToAllAdmin(requestMap.get("status") , optional.get().getEmail() , userDao.getAllAdmin());
				 
				 return cafeutils.getResponseEntity("User Status Updated Successfully", HttpStatus.OK);
			  }else {
				cafeutils.getResponseEntity("User id does not exist", HttpStatus.OK);
			  }
				
				
			}else {
				return cafeutils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
	
		
		allAdmin.remove(jwtFilter.getCurrentUser());
		System.out.println(jwtFilter.getCurrentUser());
		System.out.println(status);
		System.out.println(allAdmin);
		System.out.println(user + "-----");
		if(status!=null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "+USER:- "+user+" \n is approved by \n ADMIN:-"+ jwtFilter.getCurrentUser(), allAdmin);
			
		}else {
			
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "+USER:- "+user+" \n is diabled by \n ADMIN:-"+ jwtFilter.getCurrentUser(), allAdmin);
		}
		
		
	}

	@Override
	public ResponseEntity<String> checkToken() {
	   return cafeutils.getResponseEntity("true", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
	    try {
	    	
	    	User userObj = userDao.findByemail(jwtFilter.getCurrentUser());
	    	if(!userObj.equals(null)) {
	    		
	    		if(userObj.getPassword().equals(requestMap.get("oldPassword"))) {
	    			
	    			userObj.setPassword(requestMap.get("newPassword"));
	    			userDao.save(userObj);
	    			return cafeutils.getResponseEntity("Password updated successfully", HttpStatus.OK);
	    		}
	    		return cafeutils.getResponseEntity("Incorrect old Password", HttpStatus.BAD_REQUEST);
	    		
	    	}
	    	return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	    	
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			User user = userDao.findByemail(requestMap.get("email"));
			System.out.println(requestMap);
			if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
				emailUtils.forgotMail(user.getEmail(), "Credentials by Cafe Management System" ,user.getPassword());
			}
			return cafeutils.getResponseEntity("Check your mail for Credentials", HttpStatus.OK);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
