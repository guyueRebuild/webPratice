package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.StorageDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Storage;
import com.iTeam.service.PurchasingService;
import com.iTeam.service.Purchasing_ReturnService;
import com.iTeam.service.SalesReturnService;
import com.iTeam.service.SalesService;
import com.iTeam.service.StockInService;
import com.iTeam.service.StockOutService;
import com.iTeam.service.StorageService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Service服务层实现类
 * 
 * @author LMH
 *
 */

@Service("StorageService")
public class StorageServiceImpl implements StorageService {

	// 使用spring核心技术的IOC/DI功能进行依赖注入--实例化DAO接口
	@Resource
	private StorageDao storageDao;

	@Resource
	private SalesService salesService;

	@Resource
	private SalesReturnService salesReturnService;

	@Resource
	private PurchasingService purchasingService;

	@Resource
	private Purchasing_ReturnService purchasing_ReturnService;

	@Resource
	private StockInService stockInService;

	@Resource
	private StockOutService stockOutService;

	@Override
	public List<Storage> getStorageList(Map<String, Object> map) {
		return storageDao.getStorageList(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return storageDao.getTotal(map);
	}

	@Override
	public int add(Storage storage) throws Exception {
		try {
			return storageDao.add(storage);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("插入失败");
		}
	}

	@Override
	public int update(Storage storage) throws Exception {
		try {
			return storageDao.update(storage);
		}catch(Exception e) {
			throw new MySQLIntegrityConstraintViolationException("更新失败");
		}
	}

	@Override
	public int updateCurrentStorage(Storage storage) {
		return storageDao.updateCurrentStorage(storage);
	}

	@Override
	public int delete(Integer id) {
		try {
			//对仓库有约束的表：销售，采购，进出库
			//根据仓库编号删除销售信息
			salesService.deleteByStorageNo(id);
			salesReturnService.deleteByStorageNo(id);
			//根据仓库编号删除采购信息
			purchasingService.deleteByStorageNo(id);
			purchasing_ReturnService.deleteByStorageNo(id);
			//根据仓库编号删除进出入库信息
			stockInService.deleteByStorageNo(id);
			stockOutService.deleteByStorageNo(id);
			return storageDao.delete(id);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		try {
			// 对仓库有约束的表：销售，采购，进出库
			//根据仓库编号删除销售信息
			salesService.deleteByStorageNos(ids);
			salesReturnService.deleteByStorageNos(ids);
			// 根据仓库编号删除采购信息
			purchasingService.deleteByStorageNos(ids);
			purchasing_ReturnService.deleteByStorageNos(ids);
			// 根据仓库编号删除进出库信息
			stockInService.deleteByStorageNos(ids);
			stockOutService.deleteByStorageNos(ids);
			return storageDao.deleteBatch(ids);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
	}

}
