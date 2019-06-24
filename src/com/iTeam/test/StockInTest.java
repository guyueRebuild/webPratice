package com.iTeam.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iTeam.model.StockIn;
import com.iTeam.service.GoodsService;
import com.iTeam.service.StockInService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class StockInTest {

	@Autowired
	private StockInService service;

	// @Test
	public void findAllTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("size", 10);
		List<StockIn> list = service.findAll(map);
		for (StockIn stockIn : list) {
			System.out.println(stockIn);
		}
	}

	// @Test
	public void addStockInTest() {
		Date date = new Date();
		StockIn stockIn = new StockIn(0, 10001, 10001, 200, date, "999", "123");
		service.addStockIn(stockIn);
	}

	// @Test
	public void updateStockInTest() {
		Date date = new Date();
		StockIn stockIn = new StockIn(0, 10001, 10001, 200, date, "999", "123");
		service.addStockIn(stockIn);
	}

	//@Test
	public void deleteByStockInNo() {
		service.deleteByStockInNo(10002);
	}
}
