package com.iTeam.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.iTeam.dao.GoodsDao;
import com.iTeam.exception.SqlRollbackException;
import com.iTeam.model.Goods;
import com.iTeam.service.GoodsService;
import com.iTeam.service.PurchasingService;
import com.iTeam.service.Purchasing_ReturnService;
import com.iTeam.service.SalesReturnService;
import com.iTeam.service.SalesService;
import com.iTeam.service.StockInService;
import com.iTeam.service.StockOutService;

/**
 * 商品信息Service层实现类
 * @author XieZhiHao
 *
 */
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;
	
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
		try {
			//对商品有约束的表：销售，采购，进出库
			//根据商品编号删除销售信息，销售退货信息
			salesService.deleteByGoodsNo(goodsNo);
			salesReturnService.deleteByGoodsNo(goodsNo);
			//根据商品编号销售表删除采购及采购回退信息
			purchasingService.deleteByGoodsNo(goodsNo);
			purchasing_ReturnService.deleteByGoodsNo(goodsNo);
			//根据商品编号删除商品出入库信息
			stockInService.deleteByGoodsNo(goodsNo);
			stockOutService.deleteByGoodsNo(goodsNo);
			goodsDao.delete(goodsNo);
			goodsDao.getGoodsNoListByTypeNo(10010);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
	}

	@Override
	public int deleteBatch(List<Integer> goodsNos) {
		if(goodsNos.isEmpty()) {
			return 0;
		}
		int result=0;
		try {
			//对商品有约束的表：销售，采购，进出库
			//TODO:根据商品编号删除销售信息，销售退货信息
			salesService.deleteByGoodsNos(goodsNos);
			salesReturnService.deleteByStorageNos(goodsNos);
			//TODO:根据商品编号销售表删除采购及采购回退信息
			purchasingService.deleteByGoodsNos(goodsNos);
			purchasing_ReturnService.deleteByGoodsNos(goodsNos);
			//TODO:根据商品编号删除商品出入库信息
			stockInService.deleteByGoodsNos(goodsNos);
			stockOutService.deleteByGoodsNos(goodsNos);
		}catch(Exception e) {
			throw new SqlRollbackException("出现异常，回滚");
		}
		return result;
	}

	@Override
	public int deleteByGoodsTypeNo(Integer goodsTypeNo) {
		//根据商品类型查询商品ID
		List<Integer> goodsNos = getGoodsNoListByTypeNo(goodsTypeNo);
		//根据商品ID删除商品
		return deleteBatch(goodsNos);
	}

	@Override
	public int deleteByGoodsTypeNos(List<Integer> goodsTypeNos) {
		//根据商品类型查询商品ID
		List<Integer> goodsNos = getGoodNoListByTypeNos(goodsTypeNos);
		//根据商品ID删除商品
		return deleteBatch(goodsNos);
	}

	@Override
	public List<Integer> getGoodsNoListByTypeNo(Integer typeNo) {
		return goodsDao.getGoodsNoListByTypeNo(typeNo);
	}

	@Override
	public List<Integer> getGoodNoListByTypeNos(List<Integer> typeNos) {
		return goodsDao.getGoodNoListByTypeNos(typeNos);
	}

	@Override
	public List<Integer> getGoodsNoListByProviderNo(Integer providerNo) {
		return goodsDao.getGoodsNoListByProviderNo(providerNo);
	}

	@Override
	public List<Integer> getGoodNoListByProviderNos(List<Integer> typeNos) {
		return goodsDao.getGoodNoListByProviderNos(typeNos);
	}

}
