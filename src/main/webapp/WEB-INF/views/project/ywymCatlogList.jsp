<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>分类表列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymCatlogWebController/list">分类表列表</a></li>
    <li><a href="/sys/ywymCatlogWebController/form">分类表添加</a></li>
</ul>
<%--<form:form id="searchForm" modelAttribute="ywymCatlog" action="/sys/ywymCatlogWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>上级id：</label>
            <form:input path="pid" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>序号：</label>
            <form:input path="rank" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>分类名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>图标：</label>
            <form:input path="icon" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>级别：</label>
            <form:input path="level" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>创建时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymCatlog.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymCatlog.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li><label>更新时间：</label>
            <input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymCatlog.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymCatlog.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>--%>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">分类名称</th>
            <%--<th style="text-align: center;">上级id</th>
            <th style="text-align: center;">图标</th>
            <th style="text-align: center;">级别</th>--%>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">更新时间</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;">${entity.rank }</td>
            <td style="text-align: center;">${entity.name }</td>
            <%--<td style="text-align: center;">${entity.pid }</td>
            <td style="text-align: center;">${entity.icon }</td>
            <td style="text-align: center;">${entity.level }</td>--%>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;"><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">
                <a href="/sys/ywymCatlogWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymCatlogWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
