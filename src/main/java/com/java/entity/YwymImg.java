package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymImg extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String objectId;	//对象id
	private String type;	//类型    1礼品图片
	private String img;	//图片
	private String rank;	//排序

	public YwymImg() {
	}

	public YwymImg(String objectId, String type) {
		this.objectId = objectId;
		this.type = type;
	}
}