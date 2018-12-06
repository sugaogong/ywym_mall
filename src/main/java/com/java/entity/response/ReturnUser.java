package com.java.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@ApiModel("用户表")
public class ReturnUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("账号")
	public String username;
	@ApiModelProperty("性别：0-未知 1-男 2-女")
	public Integer gender;
	@ApiModelProperty("密码")
	public String password;
	@ApiModelProperty("手机号码")
	public String phone;
	@ApiModelProperty("昵称")
	public String nickname;
	@ApiModelProperty("头像")
	public String headImg;
	@ApiModelProperty("支付密码")
	public String passwordPay;
	@ApiModelProperty("当前积分")
	public Double score;
	@ApiModelProperty("获得总积分")
	public Double totalGetScore;
	@ApiModelProperty("消耗总积分")
	public Double totalUseScore;
	@ApiModelProperty("微信openid")
	public String openId;
	@ApiModelProperty("微信unionid")
	public String unionId;
	@ApiModelProperty("是否锁定 0否 1是")
	public String locked;
	@ApiModelProperty("登录状态 0否 1是")
	public String loginStatus;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}