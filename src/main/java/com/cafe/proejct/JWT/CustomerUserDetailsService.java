package com.cafe.proejct.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.cafe.proejct.dao.UserDao;
import com.cafe.proejct.serviceimpl.UserServiceImpl;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	UserDao userDao;
	
	//creating a bean of type bean
	private com.cafe.proejct.POJO.User userDetails = null;
	
	private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	log.info("Inside loadUserByUsername");
		
		userDetails = userDao.findByEmailId(username);
		if(!Objects.isNull(userDetails))
			return new User(userDetails.getEmail() , userDetails.getPassword() , new ArrayList<>());
		else {
			throw new UsernameNotFoundException("User not found");
		}
	}
	
	public com.cafe.proejct.POJO.User getUserDetails(){ // it will retuen complete object
		//com.cafe.proejct.POJO.User user = userDetail;
		
		return userDetails;
	}
	
	
	
	

}
