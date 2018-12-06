package com.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.java.sys.common.utils.Tool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymUser;
import com.java.dao.YwymUserDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnUser;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymUserService extends BaseService<YwymUserDao, YwymUser> {

	
	public ReturnUser getReturnUser(YwymUser entity){
		ReturnUser user = new ReturnUser();
		if(entity != null){
			try{
				BeanUtils.copyProperties(user, entity);
				if (Tool.isNotBlank(user.getPassword())){
					user.password = "******";
				}
				if (Tool.isNotBlank(user.getPasswordPay())){
					user.passwordPay = "******";
				}
				user.setNickname(Tool.emojiRecovery(user.getNickname()));
				user.setHeadImg(Tool.toUrl(user.getHeadImg()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public List<ReturnUser> getReturnUserList(List<YwymUser> entityList){
		List<ReturnUser> list = new ArrayList<ReturnUser>();
		if(entityList != null && entityList.size()>0){
			for(YwymUser entity : entityList){
				list.add(getReturnUser(entity));
			}
		}
		return list;
	}


	/**
	 * 方法名：ScoreAdd
	 * 详述：增加积分
	 * 开发人员：吴井忠
	 *
	 * @param userId
	 * @param score
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void scoreAdd(String userId, double score) throws Exception {
		if (Tool.isNotBlank(userId)) {
			if (score < 0) {
				throw new Exception("scoreAdd() : score < 0 ");
			}
			YwymUser user = getForUpdate(userId);
			if (user == null) {
				throw new Exception("scoreAdd() : user == null ");
			}
			double userScore = new BigDecimal(user.getScore()).add(new BigDecimal(score)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double getScore = new BigDecimal(user.getTotalGetScore()).add(new BigDecimal(score)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			user.setScore(userScore);
			user.setTotalGetScore(getScore);
			save(user);
		}
	}

	/**
	 * 方法名：scoreSub
	 * 详述：减少用户积分，增加消耗积分
	 * 开发人员：吴井忠
	 *
	 * @param userId
	 * @param score
	 * @throws Exception
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public synchronized void scoreSub(String userId, double score) throws Exception {
		if (Tool.isNotBlank(userId)) {
			if (score < 0) {
				throw new Exception("scoreSub() : score < 0 ");
			}
			YwymUser user = getForUpdate(userId);
			if (user == null) {
				throw new Exception("scoreSub() : user == null ");
			}
			if (score > user.getScore()) {
				throw new Exception("积分不足！");
			}
			double userScore = new BigDecimal(user.getScore()).subtract(new BigDecimal(score)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double useScore = new BigDecimal(user.getTotalUseScore()).add(new BigDecimal(score)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			user.setScore(userScore);
			user.setTotalUseScore(useScore);
			save(user);
		}
	}

}