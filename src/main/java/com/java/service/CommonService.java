package com.java.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.java.entity.YwymGoods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.common.constance.MyConstance;
import com.java.sys.common.utils.Tool;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class CommonService {
	
	@Resource
	private YwymUserService userService;
	@Resource
	private YwymGoodsService goodsService;


	/**
	 * 方法名：vcodeImg
	 * 详述：检验图形验证码，如果正确返回true
	 * @param request
	 * @param vcodeImg
	 * @return
	 */
	public boolean vcodeImg(HttpServletRequest request,String vcodeImg){
		if(Tool.isNotBlank(vcodeImg)){
			String vcodeImgSession = (String) request.getSession().getAttribute(MyConstance.KEY_VCODE_IMG);
			if(Tool.isNotBlank(vcodeImgSession)){
				vcodeImgSession = vcodeImgSession.toUpperCase();
				vcodeImg = vcodeImg.toUpperCase();
				if(vcodeImg.equals(vcodeImgSession)){
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * 方法名：scoreSubAndNumSub
	 * 详述：购买礼品减少积分 ,用户减少积分, 减少库存数量，增加消耗积分
	 * 开发人员：吴井忠
	 *
	 * @param userId
	 * @param goodsId
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void scoreSubAndNumSub(String userId, String goodsId, int num) throws Exception {
		if (Tool.isNotBlank(userId, goodsId)) {
			YwymGoods goods = goodsService.get(goodsId);
			userService.scoreSub(userId, goods.getNowScore()*num);
			goodsService.numSub(goodsId, num);
		}
	}
	
	
}
