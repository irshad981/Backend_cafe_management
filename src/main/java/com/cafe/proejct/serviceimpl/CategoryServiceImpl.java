package com.cafe.proejct.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafe.proejct.JWT.JwtFilter;
import com.cafe.proejct.POJO.Category;
import com.cafe.proejct.constents.CafeConstants;
import com.cafe.proejct.dao.CategoryDao;
import com.cafe.proejct.service.CategoryService;
import com.cafe.proejct.utils.cafeutils;

import com.google.common.base.Strings;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao  categoryDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	private static final Logger log = (Logger) LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap(requestMap ,false)) {
					categoryDao.save(getCategoryFromMap(requestMap , false));
					System.out.println("this is me");
		        return cafeutils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
				}
				
			}else {
				System.out.println("vgsdzhvk");
				return cafeutils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
				
			}
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		System.out.println("dszxfcgvhb n");
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("name")) {
			if(requestMap.containsKey("id") && validateId ) {
				return true;
			}else if(!validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String , String> requestMap ,  Boolean isAdd) {
		Category category = new Category();
		if(isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setName(requestMap.get("name"));
		
		return category;
	}

//	@Override
//	public ResponseEntity<String> addNewCategorys(Map<String, String> requestMap) {
//		try {
//			if(jwtFilter.isAdmin()) {
//				if(validateCategoryMap(requestMap ,false)) {
//					categoryDao.save(getCategoryFromMap(requestMap , false));
//                     return cafeutils.getResponseEntity("Category Addes Succesfully", HttpStatus.OK);
//				}else {
//					return cafeutils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//				}
//				
//			}
//			
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	@Override
	public ResponseEntity<String> addNewCategorys(Map<String, String> requestMap) {
	    try {
	        System.out.println("Inside try block");
	        if (jwtFilter.isAdmin()) {
	            System.out.println("isAdmin is true");
	            if (validateCategoryMap(requestMap, false)) {
	                System.out.println("Category map is validated");
	                categoryDao.save(getCategoryFromMap(requestMap, false));
	                
	                return cafeutils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
	            } else {
	                System.out.println("Category map is not valid");
	                return cafeutils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	            }
	        } else {
	            System.out.println("isAdmin is false");
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    
	    System.out.println("Returning SOMETHING_WENT_WRONG");
	    return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		try {
			if(!Strings.isNullOrEmpty(filterValue)  && filterValue.equalsIgnoreCase("true")) {
				System.out.println("fvb nm");
				log.info("Inside If");
				
				return new ResponseEntity<List<Category>>(categoryDao.getAllCategory() ,HttpStatus.OK);
			}
		    return new ResponseEntity<>(categoryDao.findAll() , HttpStatus.OK);	
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Category>>(new ArrayList() , HttpStatus.INTERNAL_SERVER_ERROR); 
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			if(jwtFilter.isAdmin()) {
				if(validateCategoryMap(requestMap , true)) {
					Optional optional =  categoryDao.findById(Integer.parseInt(requestMap.get("id")));
					if(!optional.isEmpty()) {
						categoryDao.save(getCategoryFromMap(requestMap , true));
						return cafeutils.getResponseEntity("Category Updated Successfully", HttpStatus.OK);
					}else {
						return cafeutils.getResponseEntity("Category id does not exist ", HttpStatus.OK);
					}
				}
				return cafeutils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				
			}else {
				return cafeutils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cafeutils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
