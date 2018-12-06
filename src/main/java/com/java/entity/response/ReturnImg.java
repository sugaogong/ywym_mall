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
@ApiModel("图片表")
public class ReturnImg implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("对象id")
	public String objectId;
	@ApiModelProperty("图片")
	public String img;
//	@ApiModelProperty("类型 1礼品图片")
//	public String type;
//	@ApiModelProperty("排序")
//	public String rank;
//	@ApiModelProperty("更新时间")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//	public Date updateDate;
//	@ApiModelProperty("创建时间")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
//	public Date createDate;

}