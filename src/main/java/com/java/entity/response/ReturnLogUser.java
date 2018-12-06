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
@ApiModel("用户流水记录")
public class ReturnLogUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("序号")
	public String rank;
	@ApiModelProperty("积分变动  -1支出 1入账")
	public String type;
	@ApiModelProperty("用户id")
	public String userId;
	@ApiModelProperty("对象id")
	public String objectId;
	@ApiModelProperty("积分")
	public Double score;
	@ApiModelProperty("内容")
	public String remark;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}