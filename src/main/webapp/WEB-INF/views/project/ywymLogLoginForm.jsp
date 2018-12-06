<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>用户登录日志添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymLogLoginWebController/list">用户登录日志列表</a></li>
			<li class="active"><a href="/sys/ywymLogLoginWebController/form">用户登录日志添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/sys/ywymLogLoginWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">用户id：</label>
				<div class="controls">
					<form:input path="userId" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
