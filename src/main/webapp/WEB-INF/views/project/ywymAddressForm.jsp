<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>收货地址添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymAddressWebController/list">收货地址列表</a></li>
			<li class="active"><a href="/sys/ywymAddressWebController/form">收货地址添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/sys/ywymAddressWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">用户id：</label>
				<div class="controls">
					<form:input path="userId" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">收货人：</label>
				<div class="controls">
					<form:input path="name" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">收货人电话：</label>
				<div class="controls">
					<form:input path="phone" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">省id：</label>
				<div class="controls">
					<form:input path="provId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">省：</label>
				<div class="controls">
					<form:input path="provName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">市id：</label>
				<div class="controls">
					<form:select path="cityId" class="input-large " onchange="cityChange()">
						<form:option value="" label=""/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">市：</label>
				<div class="controls">
					<form:input path="cityName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">区id：</label>
				<div class="controls">
					<form:input path="distId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">区：</label>
				<div class="controls">
					<form:input path="distName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">详细地址：</label>
				<div class="controls">
					<form:input path="address" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">标签：</label>
				<div class="controls">
					<form:input path="mark" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否默认 0否 1是：</label>
				<div class="controls">
					<form:input path="isDefault" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
