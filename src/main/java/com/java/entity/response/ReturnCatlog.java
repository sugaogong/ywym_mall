package com.java.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ApiModel("分类表")
public class ReturnCatlog implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("上级id")
	public String pid;
	@ApiModelProperty("分类名称")
	public String name;
	@ApiModelProperty("图标")
	public String icon;
	@ApiModelProperty("级别")
	public String level;
	@ApiModelProperty("排序")
	public String rank;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;


	@ApiModelProperty("礼品集合")
	public List<ReturnGoods> goodsList;
}