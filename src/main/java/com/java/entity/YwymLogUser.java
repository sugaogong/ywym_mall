package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymLogUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String rank;	//序号
	private String type;	//类型  -1支出 1入账
	private String userId;	//用户id
	private String objectId;	//对象id
	private Double score;	//积分
	private String remark;	//内容

	//查询条件
	private String username; //账号
	
}