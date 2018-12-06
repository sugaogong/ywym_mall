<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>id添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymLogHandleWebController/list">id列表</a></li>
			<li class="active"><a href="/sys/ywymLogHandleWebController/form">id添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/sys/ywymLogHandleWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">用户id：</label>
				<div class="controls">
					<form:input path="userId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">用户名：</label>
				<div class="controls">
					<form:input path="username" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容：</label>
				<div class="controls">
					<form:input path="content" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">类型：</label>
				<div class="controls">
					<form:input path="type" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">积分：</label>
				<div class="controls">
					<form:input path="score" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注：</label>
				<div class="controls">
					<form:input path="remark" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
