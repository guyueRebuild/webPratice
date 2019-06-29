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

import com.iTeam.model.StockOut;
import com.iTeam.model.StockOut;
import com.iTeam.service.StockOutService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class StockOutTest {

	@Autowired
	private StockOutService service;

	// @Test
	public void findAllTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("size", 10);
		List<StockOut> list = service.findAll(map);
		for (StockOut stockOut : list) {
			System.out.println(stockOut);
		}
	}

	// @Test
	public void addStockOutTest() throws Exception {
		Date date = new Date();
		StockOut stockOut = new StockOut(0, 10001, 10001, 200, date, "999", "123");
		service.addStockOut(stockOut);
	}

	// @Test
	public void updateStockOutTest() throws Exception {
		Date date = new Date();
		StockOut stockOut = new StockOut(10002, 10001, 10001, 200, date, "999", "1245");
		service.updateStockOut(stockOut);
	}

	@Test
	public void deleteByStockOutNo() {
		service.deleteByStockOutNo(10002);
	}

}
