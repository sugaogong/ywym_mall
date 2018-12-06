package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.java.config.YwymConfig;
import com.java.entity.YwymGoods;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymCatlog;
import com.java.dao.YwymCatlogDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnCatlog;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymCatlogService extends BaseService<YwymCatlogDao, YwymCatlog> {

	@Resource
	private YwymGoodsService goodsService;
	
	public ReturnCatlog getReturnCatlog(YwymCatlog entity){
		ReturnCatlog catlog = new ReturnCatlog();
		if(entity != null){
			try{
				BeanUtils.copyProperties(catlog, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return catlog;
	}
	
	public List<ReturnCatlog> getReturnCatlogList(List<YwymCatlog> entityList){
		List<ReturnCatlog> list = new ArrayList<ReturnCatlog>();
		if(entityList != null && entityList.size()>0){
			for(YwymCatlog entity : entityList){
				list.add(getReturnCatlog(entity));
			}
		}
		return list;
	}

	public ReturnCatlog getReturnCatlogAndGoods(YwymCatlog entity){
		ReturnCatlog catlog = new ReturnCatlog();
		if(entity != null){
			try{
				BeanUtils.copyProperties(catlog, entity);
				YwymGoods _g = new YwymGoods();
				_g.setCatlogId(catlog.getId());
				_g.setIsShelve(YwymConfig.SONE);
				_g.setIsIndex(YwymConfig.SONE);
				List<YwymGoods> goodsList = goodsService.findList(_g);
				catlog.goodsList = goodsService.getReturnGoodsList(goodsList);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return catlog;
	}

	public List<ReturnCatlog> getReturnCatlogAndGoodsList(List<YwymCatlog> entityList){
		List<ReturnCatlog> list = new ArrayList<ReturnCatlog>();
		if(entityList != null && entityList.size()>0){
			for(YwymCatlog entity : entityList){
				list.add(getReturnCatlogAndGoods(entity));
			}
		}
		return list;
	}
	
}