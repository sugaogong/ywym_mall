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
@ApiModel("用户登录日志")
public class ReturnLogLogin implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("用户id")
	public String userId;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}