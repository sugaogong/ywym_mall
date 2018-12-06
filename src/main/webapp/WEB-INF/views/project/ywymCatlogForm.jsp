<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>分类表添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymCatlogWebController/list">分类表列表</a></li>
			<li class="active"><a href="/sys/ywymCatlogWebController/form">分类表添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" enctype="multipart/form-data" action="/sys/ywymCatlogWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">序号：</label>
				<div class="controls">
					<form:input path="rank" htmlEscape="false" class="input-xlarge required" placeholder="请用英文排序 例如A,B..." maxlength="5" onkeyup="value=value.replace(/[^a-zA-Z]+/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^a-zA-Z]+/g,''))"/>
					<span style="text-align: center;font-size:22px; color:red;">*</span>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">上级id：</label>
				<div class="controls">
					<form:input path="pid" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">分类名称：</label>
				<div class="controls">
					<form:input path="name" htmlEscape="false" class="input-xlarge required"/>
					<span style="text-align: center;font-size:22px; color:red;">*</span>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">图标：</label>
				<div class="controls">
					<c:if test="${!empty entity.icon }">
						<img class="myImg" src="${entity.icon }" width="80px" height="80px"/><br>
					</c:if>
					<input type="file" name="img"/>
				</div>
			</div>--%>
			<%--<div class="control-group">
				<label class="control-label">级别：</label>
				<div class="controls">
					<form:input path="level" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
