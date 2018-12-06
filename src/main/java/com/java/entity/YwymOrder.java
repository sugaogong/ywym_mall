package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymOrder extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;	//用户id
	private String orderNo;	//订单编号
	private String goodsId;	//礼品id
	private String goodsType;	//礼品类型 1实物 2虚拟
	private String goodsName;	//礼品名称
	private String goodsIcon;	//礼品图标
	private String goodsSpec;	//礼品规格
	private Double score;	//价格（积分）
	private String receName;	//收货人姓名
	private String recePhone;	//收货人电话
	private String receAddress;	//收货人地址
	private Integer num;	//数量
	private String status;	//订单状态 1待发货 2待收货 3已完成
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date payTime;	//付款时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;	//发货时间
	private String expCode;	//快递公司代号
	private String expNo;	//快递单号
    private String remark; //备注

	//附加条件
	public String username; //用户账号

	//查询条件
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date payTimeStart; //开始付款时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date payTimeEnd;	//结束付款时间
}