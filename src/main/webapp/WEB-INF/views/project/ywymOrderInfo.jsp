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
		<br>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" enctype="multipart/form-data" action="/sys/ywymOrderWebController/save" method="post" class="form-horizontal sys-form-loading">
			<form:hidden path="id"/>
		<div style="overflow: hidden;">
			<div class="right" style="float:left; width: 40%">
				<div class="control-group">
					<label class="control-label">用户账号：</label>
					<div class="controls edit-top" >
						${entity.username}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">订单编号：</label>
					<div class="controls edit-top" >
						${entity.orderNo}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">礼品类型：</label>
					<div class="controls edit-top" >
						${fnc:getDictLabel(entity.goodsType, 'goods_type' ,' ') }
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">礼品名称：</label>
					<div class="controls edit-top" >
						${entity.goodsName}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">礼品图标：</label>
					<div class="controls edit-top" >
						<img class="myImg" src="${entity.goodsIcon }" width="80px" height="80px" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">价格（积分）：</label>
					<div class="controls edit-top" >
						${entity.score}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">订单状态 ：</label>
					<div class="controls edit-top" >
						${fnc:getDictLabel(entity.status, 'order_status' ,' ') }
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">发货时间：</label>
					<div class="controls edit-top" >
						<fmt:formatDate value="${entity.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">快递公司代号：</label>
					<div class="controls edit-top" >
						${fnc:getDictLabel(entity.expCode, 'exp_code' ,' ') }
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">快递单号：</label>
					<div class="controls edit-top" >
							${entity.expNo}
					</div>
				</div>
			</div>
			<div class="right" style="float:right; width: 48%">
				<div class="control-group">
					<label class="control-label">收货人：</label>
					<div class="controls edit-top" >
						${entity.receName}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">联系方式：</label>
					<div class="controls edit-top" >
						${entity.recePhone}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">收货地址：</label>
					<div class="controls edit-top" style="width: 300px">
						${entity.receAddress}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">数量：</label>
					<div class="controls edit-top" >
						${entity.num}
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">付款时间：</label>
					<div class="controls edit-top" >
						<fmt:formatDate value="${entity.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">留言：</label>
					<div class="controls edit-top" style="width: 300px">
						${entity.remark}
					</div>
				</div>
			<div>
		</form:form>
  	</body>
</html>
