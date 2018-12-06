package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String username;	//账号
	private Integer gender;	//性别：0-未知 1-男 2-女
	private String password;	//密码
	private String phone;	//手机号码
	private String nickname;	//昵称
	private String headImg;	//头像
	private String passwordPay;	//支付密码
	private Double score;	//当前积分
	private Double totalGetScore;	//获得总积分
	private Double totalUseScore;	//消耗总积分
	private String openId;	//微信openid
	private String unionId;	//微信unionId
	private String locked;	//是否锁定 0否 1是
	private String loginStatus; //登录状态 0否 1是
	//查询条件
	public String openIdLike; //open_id LIKE CONCAT ('%',${openId},'%')
	
}