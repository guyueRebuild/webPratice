package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.PurchasingDao;
import com.iTeam.model.Purchasing;
import com.iTeam.service.PurchasingService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Service("PurchasingService")
public class PurchasingServiceImpl implements PurchasingService {

	@Resource
	private PurchasingDao purchasingDao;
	
	@Override
	public List<Purchasing> getPurchasingList(Map<String, Object> map) {
		return purchasingDao.getPurchasingList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return purchasingDao.getTotal(map);
	}

	@Override
	public int add(Purchasing purchasing) throws Exception {
		try {
			return purchasingDao.add(purchasing);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int update(Purchasing purchasing) throws Exception {
		try {
			return purchasingDao.update(purchasing);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public int delete(Integer id) {
		return purchasingDao.delete(id);
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		return purchasingDao.deleteBatch(ids);
	}

	@Override
	public int deleteByGoodsNo(Integer goodsNo) {
		return purchasingDao.deleteByGoodsNo(goodsNo);
	}

	@Override
	public int deleteByGoodsNos(List<Integer> goodsNos) {
		return purchasingDao.deleteByGoodsNos(goodsNos);
	}

	@Override
	public int deleteByStorageNo(Integer storageNo) {
		return purchasingDao.deleteByStorageNo(storageNo);
	}

	@Override
	public int deleteByStorageNos(List<Integer> storageNos) {
		return purchasingDao.deleteByStorageNos(storageNos);
	}

}
