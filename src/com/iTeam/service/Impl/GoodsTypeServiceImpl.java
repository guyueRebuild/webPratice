package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.GoodsTypeDao;
import com.iTeam.model.GoodsType;
import com.iTeam.service.GoodsTypeService;

/**
 * 商品类别信息Service实现类
 * @author XieZhiHao
 *
 */
@Service("GoodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Resource
	private GoodsTypeDao dao;
	
	@Override
	public List<GoodsType> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return dao.getTotal(map);
	}

	@Override
	public int add(GoodsType goodsType) {
		return dao.add(goodsType);
	}

	@Override
	public int update(GoodsType goodsType) {
		return dao.update(goodsType);
	}

	@Override
	public void delete(int typeNo) {
		dao.delete(typeNo);
	}

}
