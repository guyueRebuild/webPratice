package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.GoodsDao;
import com.iTeam.model.Goods;
import com.iTeam.service.GoodsService;

/**
 * 商品信息Service层实现类
 * @author XieZhiHao
 *
 */
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	
	@Override
	public List<Goods> getGoodsList(Map<String,Object> map) {
		return goodsDao.getGoodsList(map);
	}

	@Override
	public Long getTotal(Map<String,Object> map) {
		return goodsDao.getTotal(map);
	}

	@Override
	public int add(Goods goods) {
		return goodsDao.add(goods);
	}

	@Override
	public int update(Goods goods) {
		return goodsDao.update(goods);
	}

	@Override
	public void delete(int goodsNo) {
		goodsDao.delete(goodsNo);
	}

}
