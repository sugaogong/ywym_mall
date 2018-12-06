package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.java.sys.common.utils.Tool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymOrder;
import com.java.dao.YwymOrderDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnOrder;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymOrderService extends BaseService<YwymOrderDao, YwymOrder> {

	
	public ReturnOrder getReturnOrder(YwymOrder entity){
		ReturnOrder order = new ReturnOrder();
		if(entity != null){
			try{
				BeanUtils.copyProperties(order, entity);
				order.goodsIcon = Tool.toUrl(order.getGoodsIcon());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return order;
	}
	
	public List<ReturnOrder> getReturnOrderList(List<YwymOrder> entityList){
		List<ReturnOrder> list = new ArrayList<ReturnOrder>();
		if(entityList != null && entityList.size()>0){
			for(YwymOrder entity : entityList){
				list.add(getReturnOrder(entity));
			}
		}
		return list;
	}
	
}