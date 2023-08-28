package com.cafe.proejct.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafe.proejct.dao.BillDao;
import com.cafe.proejct.dao.CategoryDao;
import com.cafe.proejct.dao.ProductDao;
import com.cafe.proejct.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	BillDao billDao;
	
	
	
	@Override
	public ResponseEntity<Map<String, Object>> getCount() {
		Map<String , Object> map = new HashMap<>();
		map.put("category", categoryDao.count());
		map.put("product", productDao.count());
		map.put("bill", billDao.count());
		return new ResponseEntity<>(map , HttpStatus.OK);
	}

}
