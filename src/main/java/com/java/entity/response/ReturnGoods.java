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
@ApiModel("礼品表")
public class ReturnGoods implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("id")
	public String id;
	@ApiModelProperty("分类id")
	public String catlogId;
	@ApiModelProperty("类型 1实物 2虚拟")
	public String type;
	@ApiModelProperty("名称")
	public String name;
	@ApiModelProperty("原价（积分）")
	public Double oldScore;
	@ApiModelProperty("现价（积分）")
	public Double nowScore;
	@ApiModelProperty("价格")
	public Double price;
	@ApiModelProperty("参考价格")
	public Double refPrice;
	@ApiModelProperty("库存")
	public Integer stock;
	@ApiModelProperty("预警库存")
	public Integer warnStock;
	@ApiModelProperty("规格")
	public String spec;
	@ApiModelProperty("缩略图")
	public String icon;
	@ApiModelProperty("礼品图片")
	public String img;
	@ApiModelProperty("礼品详情")
	public String details;
	@ApiModelProperty("上下架 0下架 1上架")
	public String isShelve;
	@ApiModelProperty("是否首页显示 0否 1是")
	public String isIndex;
	@ApiModelProperty("销售数量")
	public Integer saleNum;
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date createDate;


	@ApiModelProperty("礼品图片")
	public List<ReturnImg> imgList;
	@ApiModelProperty("礼品状态 0已下架 1在售中 2已售罄")
	public String status;

}