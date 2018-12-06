package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymLogUser;
import com.java.dao.YwymLogUserDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnLogUser;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymLogUserService extends BaseService<YwymLogUserDao, YwymLogUser> {

	
	public ReturnLogUser getReturnLogUser(YwymLogUser entity){
		ReturnLogUser logUser = new ReturnLogUser();
		if(entity != null){
			try{
				BeanUtils.copyProperties(logUser, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return logUser;
	}
	
	public List<ReturnLogUser> getReturnLogUserList(List<YwymLogUser> entityList){
		List<ReturnLogUser> list = new ArrayList<ReturnLogUser>();
		if(entityList != null && entityList.size()>0){
			for(YwymLogUser entity : entityList){
				list.add(getReturnLogUser(entity));
			}
		}
		return list;
	}
	
}