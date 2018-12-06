<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>操作日志列表</title>
    <%@ include file="/WEB-INF/views/include/style.jsp"%>
</head>
<body>
<ul class="nav nav-tabs sys-font">
    <li class="active"><a href="/sys/ywymLogHandleWebController/list">操作日志列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="ywymLogHandle" action="/sys/ywymLogHandleWebController/list" method="post" class="breadcrumb form-search">
    <ul class="ul-form">
        <li><label>用户名：</label>
            <form:input path="username" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>内容：</label>
            <form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>操作时间：</label>
            <input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogHandle.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',isShowClear:true});"/> -
            <input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${ywymLogHandle.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',isShowClear:true});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
        <li class="clear"></li>
    </ul>
</form:form>
<sys:message content="${message }"/>
<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
    <thead class="sys-font">
        <tr>
            <th style="text-align: center;">操作时间</th>
            <th style="text-align: center;">用户名</th>
            <th style="text-align: center;">内容</th>
            <th style="text-align: center;">类型</th>
            <th style="text-align: center;">积分</th>
            <th style="text-align: center;">备注</th>
            <%--<th style="text-align: center;">操作</th>--%>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="entity">
        <tr>
            <td style="text-align: center;"><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td style="text-align: center;">${entity.username }</td>
            <td style="text-align: center;">${entity.content }</td>
            <td style="text-align: center;">${entity.type }</td>
            <td style="text-align: center;">${entity.score }</td>
            <td style="text-align: center;">${entity.remark }</td>
            <%--<td style="text-align: center;">
                <a href="/sys/ywymLogHandleWebController/form?id=${entity.id }">修改</a>
                <a href="/sys/ywymLogHandleWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
            </td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<sys:page page="${page }"/>
</body>
</html>
