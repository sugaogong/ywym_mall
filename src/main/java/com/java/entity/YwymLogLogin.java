package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;

@Getter
@Setter
public class YwymLogLogin extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;	//用户id

	public YwymLogLogin() {
	}

	public YwymLogLogin(String userId) {
		this.userId = userId;
	}
}