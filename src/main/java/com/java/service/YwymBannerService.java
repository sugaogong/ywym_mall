package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.java.sys.common.utils.Tool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymBanner;
import com.java.dao.YwymBannerDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnBanner;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymBannerService extends BaseService<YwymBannerDao, YwymBanner> {

	
	public ReturnBanner getReturnBanner(YwymBanner entity){
		ReturnBanner banner = new ReturnBanner();
		if(entity != null){
			try{
				BeanUtils.copyProperties(banner, entity);
				banner.img = Tool.toUrl(banner.getImg());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return banner;
	}
	
	public List<ReturnBanner> getReturnBannerList(List<YwymBanner> entityList){
		List<ReturnBanner> list = new ArrayList<ReturnBanner>();
		if(entityList != null && entityList.size()>0){
			for(YwymBanner entity : entityList){
				list.add(getReturnBanner(entity));
			}
		}
		return list;
	}
	
}