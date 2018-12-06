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
@ApiModel("收货地址")
public class ReturnAddress implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("用户id")
	public String userId;
	@ApiModelProperty("收货人")
	public String name;
	@ApiModelProperty("收货人电话")
	public String phone;
	@ApiModelProperty("省id")
	public String provId;
	@ApiModelProperty("省")
	public String provName;
	@ApiModelProperty("市id")
	public String cityId;
	@ApiModelProperty("市")
	public String cityName;
	@ApiModelProperty("区id")
	public String distId;
	@ApiModelProperty("区")
	public String distName;
	@ApiModelProperty("详细地址")
	public String address;
	@ApiModelProperty("省市区编码")
	public String areaCode;
	@ApiModelProperty("标签")
	public String mark;
	@ApiModelProperty("是否默认 0否 1是")
	public String isDefault;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}