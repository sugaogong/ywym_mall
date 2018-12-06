package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymAddress;
import com.java.dao.YwymAddressDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnAddress;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymAddressService extends BaseService<YwymAddressDao, YwymAddress> {

	
	public ReturnAddress getReturnAddress(YwymAddress entity){
		ReturnAddress address = new ReturnAddress();
		if(entity != null){
			try{
				BeanUtils.copyProperties(address, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return address;
	}
	
	public List<ReturnAddress> getReturnAddressList(List<YwymAddress> entityList){
		List<ReturnAddress> list = new ArrayList<ReturnAddress>();
		if(entityList != null && entityList.size()>0){
			for(YwymAddress entity : entityList){
				list.add(getReturnAddress(entity));
			}
		}
		return list;
	}
	
}