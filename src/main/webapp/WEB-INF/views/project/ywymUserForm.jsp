<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>用户表添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymUserWebController/list">用户表列表</a></li>
			<li class="active"><a href="/sys/ywymUserWebController/form">用户表添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/sys/ywymUserWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">账号：</label>
				<div class="controls">
					<form:input path="username" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">性别：</label>
				<div class="controls">
					<%--<form:input path="gender" htmlEscape="false" class="input-xlarge "/>--%>
					<form:select path="gender" class="input-xlarge " >
						<form:option value="" label=""/>
						<form:options items="${fnc:getDictList('gender')}"   htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">密码：</label>
				<div class="controls">
					<form:input path="password" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">手机号码：</label>
				<div class="controls">
					<form:input path="phone" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">昵称：</label>
				<div class="controls">
					<form:input path="nickname" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">头像：</label>
				<div class="controls">
					<form:input path="headImg" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">支付密码：</label>
				<div class="controls">
					<form:input path="payPassword" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">当前积分：</label>
				<div class="controls">
					<form:input path="score" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">获得总积分：</label>
				<div class="controls">
					<form:input path="totalGetScore" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">消耗总积分：</label>
				<div class="controls">
					<form:input path="totalUseScore" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">微信openid：</label>
				<div class="controls">
					<form:input path="openId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">是否锁定：</label>
				<div class="controls">
					<%--<form:input path="locked" htmlEscape="false" class="input-xlarge "/>--%>
					<form:select path="locked" class="input-xlarge ">
						<form:option value="" label=""/>
						<form:options items="${fnc:getDictList('locked')}"   htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
