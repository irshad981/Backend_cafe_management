package com.cafe.proejct.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cafe.proejct.wrapper.UserWrapper;

@CrossOrigin
@RequestMapping(path = "/user")
public interface UserRest {
	
	@PostMapping(path= "/signup")
	public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String , String> requestmap);

	
	@PostMapping(path= "/login")
	public ResponseEntity<String > login(@RequestBody(required = true) Map<String , String> requestmap);
	
	@GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();
	
	@PostMapping(path = "/update")
	public ResponseEntity<String> update(@RequestBody(required = true)  Map<String , String >requestMap);
	
	@GetMapping(path = "/checkToken")
	ResponseEntity<String> checkToken();
	
	@PostMapping(path = "/changePassword")
	ResponseEntity<String> changePassword(@RequestBody Map<String , String > requestmap);
	
	@PostMapping(path = "/forgetPassword")
	ResponseEntity<String> forgetPassword(@RequestBody Map<String , String> requestMap);
	
	
	
}

