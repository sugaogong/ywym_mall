<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>订单添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="/sys/ywymOrderWebController/list">订单列表</a></li>
			<li class="active"><a href="/sys/ywymOrderWebController/form">订单添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" enctype="multipart/form-data" action="/sys/ywymOrderWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
			<%--<div class="control-group">
				<label class="control-label">用户id：</label>
				<div class="controls">
					<form:input path="userId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">订单编号：</label>
				<div class="controls">
					<form:input path="orderNo" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">礼品id：</label>
				<div class="controls">
					<form:input path="goodsId" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>--%>
			<div class="control-group">
				<label class="control-label">礼品类型：</label>
				<form:select path="goodsType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fnc:getDictList('goods_type')}"   htmlEscape="false"/>
				</form:select>
			</div>
			<div class="control-group">
				<label class="control-label">礼品名称：</label>
				<div class="controls">
					<form:input path="goodsName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">礼品图标：</label>
				<div class="controls">
					<form:input path="goodsIcon" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">价格（积分）：</label>
				<div class="controls">
					<form:input path="score" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">收货人：</label>
				<div class="controls">
					<form:input path="receName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">联系方式：</label>
				<div class="controls">
					<form:input path="recePhone" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">收货地址：</label>
				<div class="controls">
					<form:input path="receAddress" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">数量：</label>
				<div class="controls">
					<form:input path="num" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">订单状态 ：</label>
				<div class="controls">
					<%--<form:input path="status" htmlEscape="false" class="input-xlarge "/>--%>
					<form:select path="status" class="input-medium">
						<form:option value="" label=""/>
						<form:options items="${fnc:getDictList('order_status')}"   htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">付款时间：</label>
				<div class="controls">
					<input name="payTime" type="text" maxlength="20" class="input-medium Wdate " readOnly="true" value="<fmt:formatDate value="${entity.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">发货时间：</label>
				<div class="controls">
					<input name="sendTime" type="text" maxlength="20" class="input-medium Wdate " readOnly="true" value="<fmt:formatDate value="${entity.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">快递公司代号：</label>
				<div class="controls">
					<form:input path="expCode" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">快递单号：</label>
				<div class="controls">
					<form:input path="expNo" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
