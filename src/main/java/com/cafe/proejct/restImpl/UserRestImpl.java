package com.cafe.proejct.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.proejct.constents.CafeConstants;
import com.cafe.proejct.rest.UserRest;
import com.cafe.proejct.service.UserService;
import com.cafe.proejct.utils.cafeutils;
import com.cafe.proejct.wrapper.UserWrapper;

@RestController
@CrossOrigin
public class UserRestImpl implements UserRest {

	@Autowired
	UserService userService;
	
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestmap) {
		try {
			return userService.signUp(requestmap);
			
		}catch(Exception ex ) {
			ex.printStackTrace();
		}
		//return new ResponseEntity<String>("{\"message\":\"Something went wrong\"}" ,HttpStatus.INTERNAL_SERVER_ERROR );
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


	@Override
	public ResponseEntity<String> login(Map<String, String> requestmap) {
		try {
			return userService.login(requestmap);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {
		try {
			return userService.getAllUser();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			return userService.update(requestMap);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> checkToken() {
		try {
			
			return userService.checkToken();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			return userService.changePassword(requestMap);
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
		try {
			return userService.forgotPassword(requestMap);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
