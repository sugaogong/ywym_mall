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
@ApiModel("订单")
public class ReturnOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("用户id")
	public String userId;
	@ApiModelProperty("订单编号")
	public String orderNo;
	@ApiModelProperty("礼品id")
	public String goodsId;
	@ApiModelProperty("礼品类型 1实物 2虚拟")
	public String goodsType;
	@ApiModelProperty("礼品名称")
	public String goodsName;
	@ApiModelProperty("礼品图标")
	public String goodsIcon;
	@ApiModelProperty("礼品规格")
	public String goodsSpec;
	@ApiModelProperty("价格（积分）")
	public Double score;
	@ApiModelProperty("收货人姓名")
	public String receName;
	@ApiModelProperty("收货人电话")
	public String recePhone;
	@ApiModelProperty("收货人地址")
	public String receAddress;
	@ApiModelProperty("数量")
	public Integer num;
	@ApiModelProperty("订单状态 1待发货 2待收货 3已完成")
	public String status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty("付款时间")
	public Date payTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty("发货时间")
	public Date sendTime;
	@ApiModelProperty("快递公司代号")
	public String expCode;
	@ApiModelProperty("快递单号")
	public String expNo;
	@ApiModelProperty("备注")
	public String remark;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;

}