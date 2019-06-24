package com.iTeam.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iTeam.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class GoodsTest {

	@Autowired
	private GoodsService service;
	
//	@Test
//	public void getGoodsListTest() {
//		List<Goods> list = service.getGoodsList();
//		for(Goods good : list) {
//			System.out.println(good.getGoodsName());
//		}
//	}
}
