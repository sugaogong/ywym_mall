package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymLogLogin;
import com.java.dao.YwymLogLoginDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnLogLogin;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymLogLoginService extends BaseService<YwymLogLoginDao, YwymLogLogin> {

	
	public ReturnLogLogin getReturnLogLogin(YwymLogLogin entity){
		ReturnLogLogin logLogin = new ReturnLogLogin();
		if(entity != null){
			try{
				BeanUtils.copyProperties(logLogin, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return logLogin;
	}
	
	public List<ReturnLogLogin> getReturnLogLoginList(List<YwymLogLogin> entityList){
		List<ReturnLogLogin> list = new ArrayList<ReturnLogLogin>();
		if(entityList != null && entityList.size()>0){
			for(YwymLogLogin entity : entityList){
				list.add(getReturnLogLogin(entity));
			}
		}
		return list;
	}
	
}