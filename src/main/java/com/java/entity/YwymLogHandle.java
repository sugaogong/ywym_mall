package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymLogHandle extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;	//用户id
	private String username;	//用户名
	private String content;	//内容
	private String type;	//类型
	private String score;	//积分
	private String remark;	//备注
	
}