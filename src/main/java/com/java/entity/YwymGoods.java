package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymGoods extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String catlogId;	//分类id
	private String type;	//类型 1实物 2虚拟
	private String name;	//名称
	private Double oldScore;	//原价（积分）
	private Double nowScore;	//现价（积分）
	private Double price;	//价格
	private Double refPrice;	//参考价格
	private Integer stock;	//库存
	private Integer warnStock;	//预警库存
	private String spec;	//规格
	private String icon;	//缩略图
	private String img;	//礼品图片
	private String details;	//礼品详情
	private String isIndex;	//是否首页显示 0否 1是
	private String isShelve;	//上下架 0下架 1上架
	private Integer saleNum;	//销售数量
	private String rank; //排序

	//附加条件
	public String catlogName;//礼品类目
	public String status; //状态 0已下架 1在售中 2已售罄

	//查询条件
	public String keyword; //关键字

}