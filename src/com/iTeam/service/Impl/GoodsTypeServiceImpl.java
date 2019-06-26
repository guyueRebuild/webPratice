package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.GoodsTypeDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.GoodsType;
import com.iTeam.service.GoodsService;
import com.iTeam.service.GoodsTypeService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * 商品类别信息Service实现类
 * @author XieZhiHao
 *
 */
@Service("GoodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Resource
	private GoodsTypeDao dao;
	
	@Resource
	private GoodsService goodsService;
	
	@Override
	public List<GoodsType> list(Map<String, Object> map) {
		return dao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return dao.getTotal(map);
	}

	@Override
	public int add(GoodsType goodsType) throws Exception {
		try {
			return dao.add(goodsType);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
		
	}

	@Override
	public int update(GoodsType goodsType) throws Exception{
		try {
			return dao.update(goodsType);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public void delete(int typeNo) {
		try {
			//对商品类型有约束的表：商品，
			//根据商品类型编号删除商品
			goodsService.deleteByGoodsTypeNo(typeNo);
			dao.delete(typeNo);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
	}

	@Override
	public void deleteBatch(List<Integer> typeNos) {
		try {
			//对商品类型有约束的表：商品，
			//根据商品类型删除商品
			goodsService.deleteByGoodsTypeNos(typeNos);
			dao.deleteBatch(typeNos);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
	}

}
