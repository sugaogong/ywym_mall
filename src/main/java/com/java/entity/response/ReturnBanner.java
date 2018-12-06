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
@ApiModel("轮播图")
public class ReturnBanner implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("标题")
	public String title;
	@ApiModelProperty("类型 1链接 2礼品")
	public String type;
	@ApiModelProperty("图片")
	public String img;
	@ApiModelProperty("链接")
	public String link;
	@ApiModelProperty("对象id")
	public String objectId;
	@ApiModelProperty("是否开启 0否 1是")
	public String status;
	@ApiModelProperty("排序")
	public String rank;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}