package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.${table.className};
import com.java.dao.${table.className}Dao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.Return${table.shortName};

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ${table.className}Service extends BaseService<${table.className}Dao, ${table.className}> {

	
	public Return${table.shortName} getReturn${table.shortName}(${table.className} entity){
		Return${table.shortName} ${table.shortName?uncap_first} = new Return${table.shortName}();
		if(entity != null){
			try{
				BeanUtils.copyProperties(${table.shortName?uncap_first}, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ${table.shortName?uncap_first};
	}
	
	public List<Return${table.shortName}> getReturn${table.shortName}List(List<${table.className}> entityList){
		List<Return${table.shortName}> list = new ArrayList<Return${table.shortName}>();
		if(entityList != null && entityList.size()>0){
			for(${table.className} entity : entityList){
				list.add(getReturn${table.shortName}(entity));
			}
		}
		return list;
	}
	
}