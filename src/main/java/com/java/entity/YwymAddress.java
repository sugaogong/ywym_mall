package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymAddress extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;	//用户id
	private String name;	//收货人
	private String phone;	//收货人电话
	private String provId;	//省id
	private String provName;	//省
	private String cityId;	//市id
	private String cityName;	//市
	private String distId;	//区id
	private String distName;	//区
	private String address;	//详细地址
	private String mark;	//标签
	private String isDefault;	//是否默认 0否 1是
	private String areaCode; //省市区编码
	
}