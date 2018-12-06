package com.java.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import com.java.sys.common.basic.entity.BaseEntity;
import java.util.Date;
<#list table.columnList as col>
<#if col.javaType == "BigDecimal">
import java.math.BigDecimal;
<#break>
</#if>
</#list>

@Getter
@Setter
public class ${table.className} extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	<#list table.columnList as col>
	<#if col.columnName != "id" && col.columnName != "create_date" && col.columnName != "update_date">
	<#if col.javaType == "Date">
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	</#if>
	private ${col.javaType} ${col.attrName};	//${col.columnComment}
	</#if>
	</#list>
	
}