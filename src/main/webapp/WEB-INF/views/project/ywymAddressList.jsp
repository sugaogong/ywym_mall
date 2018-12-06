<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>收货地址列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymAddressWebController/list">收货地址列表</a></li>
    <li><a href="/sys/ywymAddressWebController/form">收货地址添加</a></li>
</ul>
<form:form id="searchForm" modelAttribute="ywymAddress" action="/sys/ywymAddressWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>用户id：</label>
            <form:input path="userId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>收货人：</label>
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>收货人电话：</label>
            <form:input path="phone" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>省id：</label>
            <form:input path="provId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>省：</label>
            <form:input path="provName" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>市id：</label>
            <form:input path="cityId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>市：</label>
            <form:input path="cityName" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>区id：</label>
            <form:input path="distId" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>区：</label>
            <form:input path="distName" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>详细地址：</label>
            <form:input path="address" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>标签：</label>
            <form:input path="mark" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>是否默认 0否 1是：</label>
            <form:input path="isDefault" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>创建时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymAddress.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymAddress.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymAddress.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymAddress.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">用户id</th>
            <th style="text-align: center;">收货人</th>
            <th style="text-align: center;">收货人电话</th>
            <th style="text-align: center;">省id</th>
            <th style="text-align: center;">省</th>
            <th style="text-align: center;">市id</th>
            <th style="text-align: center;">市</th>
            <th style="text-align: center;">区id</th>
            <th style="text-align: center;">区</th>
            <th style="text-align: center;">详细地址</th>
            <th style="text-align: center;">标签</th>
            <th style="text-align: center;">是否默认 0否 1是</th>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.userId }</td>
            <td style="text-align: center;">${entity.name }</td>
            <td style="text-align: center;">${entity.phone }</td>
            <td style="text-align: center;">${entity.provId }</td>
            <td style="text-align: center;">${entity.provName }</td>
            <td style="text-align: center;">${entity.cityId }</td>
            <td style="text-align: center;">${entity.cityName }</td>
            <td style="text-align: center;">${entity.distId }</td>
            <td style="text-align: center;">${entity.distName }</td>
            <td style="text-align: center;">${entity.address }</td>
            <td style="text-align: center;">${entity.mark }</td>
            <td style="text-align: center;">${entity.isDefault }</td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <a href="/sys/ywymAddressWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymAddressWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
