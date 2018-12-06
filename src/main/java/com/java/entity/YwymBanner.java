package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymBanner extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String title;	//标题
	private String img;	//图片
	private String link;	//链接
	private String status;	//是否开启 0否 1是
	private String type;	//类型 1链接 2礼品
	private String rank;	//排序
	private String objectId; //对象id

	
}