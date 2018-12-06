package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.java.sys.common.utils.Tool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymImg;
import com.java.dao.YwymImgDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnImg;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymImgService extends BaseService<YwymImgDao, YwymImg> {

	
	public ReturnImg getReturnImg(YwymImg entity){
		ReturnImg img = new ReturnImg();
		if(entity != null){
			try{
				BeanUtils.copyProperties(img, entity);
				img.img = Tool.toUrl(img.getImg());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public List<ReturnImg> getReturnImgList(List<YwymImg> entityList){
		List<ReturnImg> list = new ArrayList<ReturnImg>();
		if(entityList != null && entityList.size()>0){
			for(YwymImg entity : entityList){
				list.add(getReturnImg(entity));
			}
		}
		return list;
	}
	
}