package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymCatlog extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String pid;	//上级id
	private String name;	//分类名称
	private String icon;	//图标
	private String level;	//级别
	private String rank;	//排序
	
}