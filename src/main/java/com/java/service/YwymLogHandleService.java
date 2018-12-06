package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymLogHandle;
import com.java.dao.YwymLogHandleDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnLogHandle;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymLogHandleService extends BaseService<YwymLogHandleDao, YwymLogHandle> {

	
	public ReturnLogHandle getReturnLogHandle(YwymLogHandle entity){
		ReturnLogHandle logHandle = new ReturnLogHandle();
		if(entity != null){
			try{
				BeanUtils.copyProperties(logHandle, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return logHandle;
	}
	
	public List<ReturnLogHandle> getReturnLogHandleList(List<YwymLogHandle> entityList){
		List<ReturnLogHandle> list = new ArrayList<ReturnLogHandle>();
		if(entityList != null && entityList.size()>0){
			for(YwymLogHandle entity : entityList){
				list.add(getReturnLogHandle(entity));
			}
		}
		return list;
	}
	
}